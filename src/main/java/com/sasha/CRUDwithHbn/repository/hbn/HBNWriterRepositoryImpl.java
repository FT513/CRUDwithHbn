package com.sasha.CRUDwithHbn.repository.hbn;

import com.sasha.CRUDwithHbn.model.Writer;
import com.sasha.CRUDwithHbn.repository.WriterRepository;
import com.sasha.CRUDwithHbn.utils.HbnUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HBNWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer getById(Integer id) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = String.format("FROM Writer writer inner join fetch w.posts where writer.id = %d", id);
        Writer writer = (Writer) session.createQuery(hql);
        transaction.commit();
        session.close();
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(writer);
        transaction.commit();
        session.close();
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = String.format("FROM Writer writer inner join fetch Post post inner join fetch Label label where writer.id = %d", id);
        Writer writer = (Writer) session.createQuery(hql);
        session.delete(writer);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Writer> getAll() {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Writer writer inner join fetch Post post inner join fetch Label label";
        List writers = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        writer.setId((Integer)session.save(writer));
        transaction.commit();
        session.close();
        return writer;
    }
}
