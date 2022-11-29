package com.sasha.CRUDwithHbn.repository.hbn;

import com.sasha.CRUDwithHbn.model.Post;
import com.sasha.CRUDwithHbn.model.PostStatus;
import com.sasha.CRUDwithHbn.repository.PostRepository;
import com.sasha.CRUDwithHbn.utils.HbnUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HBNPostRepositoryImpl implements PostRepository {
    @Override
    public Post getById(Integer id) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = String.format("FROM Post post inner join fetch Label label where post.id = %d", id);
        Post post = (Post) session.createQuery(hql);
        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public Post update(Post post) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(post);
        transaction.commit();
        session.close();
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        Post post = getById(id);
        post.setPostStatus(PostStatus.DELETED);
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(post);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Post> getAll() {
        Session session = HbnUtils.getSession();
        String hql = String.format("FROM Post as post " +
                "inner join fetch Label as label " +
                "where post.PostStatus not equals %s", "DELETE");
        List posts = session.createQuery(hql).list();
        return posts;
    }

    @Override
    public Post save(Post post) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        post.setId((Integer)session.save(post));
        transaction.commit();
        session.close();
        return post;
    }
}
