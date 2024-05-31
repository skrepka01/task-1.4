package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String NAME = "postgres";
    private static final String PASSWORD = "postgres";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.connected()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE users12(ID BIGINT,NAME VARCHAR(128),LASTNAME VARCHAR(128),AGE INT)");
            System.out.println("Таблица создана");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.connected()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE users12");
            System.out.println("Таблица создана");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(long id, String name, String lastName, byte age) {
        try (Connection connection = Util.connected()) {
            String sql = "INSERT INTO users12 (ID,NAME, LASTNAME, AGE) VALUES (?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, (int) id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, lastName);
                preparedStatement.setInt(4, age);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("Пользователь введён");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.connected()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users12 WHERE ID = " + id);
            System.out.println("Пользователь удалён");
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection1 = Util.connected()) {
            Statement statement = connection1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users12");
            if (!resultSet.next()) {
                resultSet.close();
                statement.close();
            }
            while (resultSet.next()) {
                long id = resultSet.getInt("id");
                String name = resultSet.getString("NAME");
                String lastname = resultSet.getString("LASTNAME");
                byte age = resultSet.getByte("AGE");
                list.add(new User(id, name, lastname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.connected()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM users12");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.connected()) {
            String sql = "INSERT INTO users12 (NAME, LASTNAME, AGE) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, age);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("Пользователь введён");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
