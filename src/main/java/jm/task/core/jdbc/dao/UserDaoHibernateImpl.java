package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
       try {
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
       }catch (Exception e){
           transaction.rollback();
           throw new RuntimeException();
       }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DROP TABLE if EXISTS users");
            query.executeUpdate();
            transaction.commit();
            session.close();
        }catch (Exception e ){
            transaction.rollback();
            throw new RuntimeException();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session =Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = new User(name,lastName,age);
            session.persist(user);
            transaction.commit();
            session.close();
        }catch (Exception e ){
            transaction.rollback();
            throw new RuntimeException();
        }

    }

    @Override
    public void removeUserById(long id) {;
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(session.get(User.class,id));
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            throw  new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = new ArrayList<>();
        try {
            session.get(User.class, 1L);

            Query cq =session.createQuery("from User");

            userList = cq.getResultList();
            session.close();

        }catch (Exception e){
            transaction.rollback();
            throw  new RuntimeException();
        }
        return  userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Query query = session.createSQLQuery("TRUNCATE users");
            query.executeUpdate();
            transaction.commit();
            session.close();
        }catch (Exception e){
            transaction.rollback();
            throw  new RuntimeException();
        }

    }
}
