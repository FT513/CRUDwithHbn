package com.sasha.CRUDwithHbn.repository.hbn;

import com.sasha.CRUDwithHbn.model.Post;
import com.sasha.CRUDwithHbn.model.PostStatus;
import com.sasha.CRUDwithHbn.repository.PostRepository;
import com.sasha.CRUDwithHbn.utils.HbnUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HBNPostRepositoryImpl implements PostRepository {
    @Override
    public Post getById(Integer id) {
        Session session = HbnUtils.getSession();
        Post post = session.get(Post.class, id);
        Hibernate.initialize(post);
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
        List<Post> posts = session.createQuery("from Post as p where p.postStatus = 'ACTIVE'").list();
        return posts;
    }

    @Override
    public Post save(Post post) {
        post.setPostStatus(PostStatus.ACTIVE);
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        post.setId((Integer)session.save(post));
        transaction.commit();
        session.close();
        return post;
    }
}
