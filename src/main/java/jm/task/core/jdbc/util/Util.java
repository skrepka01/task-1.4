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

    public static Connection connected() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void setQuery(String sql) {
        try (Connection connection = connected()) {
            Statement statement = createStatement(connection);
            if (statement == null) {
                return;
            }
            statement.executeQuery(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return;
        }
    }

    public static SessionFactory getSession(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }

    private static Statement createStatement(Connection connection) {
        try {
            if (connection == null) {
                return null;
            }
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static class SQLGetter {

        public static List<User> getList() {
            List<User> list = new ArrayList<>();
            try {
                Connection connection1 = DriverManager.getConnection(URL, NAME, PASSWORD);
                Statement statement = connection1.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users12");
                if (!resultSet.next()) {
                    resultSet.close();
                    statement.close();
                    connection1.close();
                }
                while (resultSet.next()){
                    long id = resultSet.getInt("id");
                    String name = resultSet.getString("NAME");
                    //list.add(new User(id,name));
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }return list;
        }
    }
}
