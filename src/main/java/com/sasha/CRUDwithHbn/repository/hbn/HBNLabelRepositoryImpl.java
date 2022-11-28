package com.sasha.CRUDwithHbn.repository.hbn;

import com.sasha.CRUDwithHbn.model.Label;
import com.sasha.CRUDwithHbn.repository.LabelRepository;
import com.sasha.CRUDwithHbn.utils.HbnUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HBNLabelRepositoryImpl implements LabelRepository {
    @Override
    public Label getById(Integer id) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Label label = session.get(Label.class, id);
        transaction.commit();
        session.close();
        return label;
    }

    @Override
    public Label update(Label label) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(label);
        transaction.commit();
        session.close();
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.beginTransaction();
        Label label = session.get(Label.class, id);
        session.delete(label);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Label> getAll() {
        Session session = HbnUtils.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List labels = session.createQuery("FROM Label").list();

        session.close();
        return labels;
    }

    @Override
    public Label save(Label label) {
        Session session = HbnUtils.getSession();
        session.beginTransaction();
        label.setId((Integer) session.save(label));
        session.getTransaction().commit();
        session.close();
        return label;
    }
}
