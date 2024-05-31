package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static Connection connection;

    public Util() {
    }

    public static Connection connected() throws SQLException {
        return connection = DriverManager.getConnection(URL,NAME,PASSWORD);
    }

    public static SessionFactory getSession(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }

}
