package com.sasha.CRUDwithHbn.repository.hbn;

import com.sasha.CRUDwithHbn.model.Post;
import com.sasha.CRUDwithHbn.model.Writer;
import com.sasha.CRUDwithHbn.repository.WriterRepository;
import com.sasha.CRUDwithHbn.utils.HbnUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HBNWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer getById(Integer id) {
        Session session = HbnUtils.getSession();
        Writer writer = session.get(Writer.class, id);
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
        Query query = session.createQuery("delete Writer where id =:id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<Writer> getAll() {
        Session session = HbnUtils.getSession();
        List<Writer> writers = session.createQuery("from Writer").list();
        session.close();
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
    Session session = HbnUtils.getSession();
    writer.setId((Integer)session.save(writer));
    session.close();
    return writer;
    }
}
