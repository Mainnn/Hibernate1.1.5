package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        Properties properties =new Properties();
        properties.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/users");
        properties.setProperty("hibernate.connection.username","root");
        properties.setProperty("hibernate.connection.password","root");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.current_session_context_class","thread");
        //properties.setProperty("hibernate.format_sql","true");
        //properties.setProperty("hibernate.hbm2ddl.auto","create");
        properties.setProperty("show_sql, true","create");

        sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/users")
                .setProperty("hibernate.connection.username","root")
                .setProperty("hibernate.connection.password","root")
                .setProperty("show_sql, true","create")
                .setProperty("hibernate.current_session_context_class","thread")
                .setProperty("hibernate.current_session_context_class","thread")
                .setProperty("hibernate.connection.autocommit","true")
                .addAnnotatedClass(User.class).
                buildSessionFactory();

    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory = new Configuration()
                .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/users")
                .setProperty("hibernate.connection.username","root")
                .setProperty("hibernate.connection.password","root")
                .setProperty("show_sql, true","create")
                .setProperty("hibernate.current_session_context_class","thread")
                .setProperty("hibernate.current_session_context_class","thread")
                .setProperty("hibernate.connection.autocommit","true")
                .addAnnotatedClass(User.class).
                buildSessionFactory();
    }

}
