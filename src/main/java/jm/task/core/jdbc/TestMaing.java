package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestMaing {
    private static UserService service = new UserServiceImpl();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.printf("lj");
        runHibernate();
        System.out.printf("Done");
        //runJDBC();
    }
    private static void runJDBC(){
        service.createUsersTable();
        List<User> userList = new ArrayList<>();
        userList.add(new User("Tommi","Fommi",(byte) 10));
        userList.add(new User("Pig","Pig",(byte) 10));
        userList.add(new User("Pen","Fommi",(byte) 10));
        userList.add(new User("Ken","Fommi",(byte) 1));
        for (User user:userList) {
            service.saveUser(user.getName(),user.getLastName(),user.getAge());
            System.out.format("User с именем – %s добавлен в базу данных",user.getName());
            System.out.println();
        }
        userList = service.getAllUsers();
        User us = userList.get(1);
        userList.stream()
                .forEachOrdered(x -> System.out.println(x.toString()));
        service.cleanUsersTable();
        service.dropUsersTable();
    }
    private static void runHibernate(){
        Session session = Util.getSessionFactory().openSession();
        UserDao userDao = new UserDaoHibernateImpl();
        List<User> userList = new ArrayList<>();
        userDao.createUsersTable();
        session.save(new User("Tom", "One", (byte) 1));
        session.save(new User("Tom", "Two", (byte) 2));
        session.save(new User("Tom", "Fhree", (byte) 3));
        session.save(new User("Tom", "four", (byte) 4));
        userList =service.getAllUsers();
        userList.stream()
                .forEachOrdered(x -> System.out.println(x.toString()));
        //userDao.cleanUsersTable();
        //userDao.dropUsersTable();
        session.beginTransaction().commit();

        session.close();
    }
}
