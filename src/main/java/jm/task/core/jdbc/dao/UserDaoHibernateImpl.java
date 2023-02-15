package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("create table if NOT EXISTS users.users\n" +
                "(\n" +
                "    id       bigint auto_increment\n" +
                "        primary key,\n" +
                "    age      tinyint      null,\n" +
                "    lastName varchar(255) null,\n" +
                "    name     varchar(255) null\n" +
                ")\n" +
                "    engine = MyISAM;\n");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE if EXISTS users");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction txt1 = session.beginTransaction();
        User user = new User(name,lastName,age);
        session.persist(user);
        txt1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(session.get(User.class,id));
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    @Override
    public void cleanUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("TRUNCATE users");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
