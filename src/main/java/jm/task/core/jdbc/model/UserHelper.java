package jm.task.core.jdbc.model;

import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserHelper {
    private SessionFactory sessionFactory;

    public UserHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public List<User> getUsersList(){
        Session session = sessionFactory.openSession();
        session.get(User.class, 1L);

        CriteriaBuilder cd = session.getCriteriaBuilder();

        CriteriaQuery cq =cd.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.select(root);

        Query query = session.createQuery(cq);
        List<User> userList = query.getResultList();
        session.close();
        return  userList;
    }
}
