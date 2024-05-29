package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        //userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser(1L,"Alexo","Moore", (byte) 9);

        System.out.println(userDaoJDBC.getAllUsers());
       // new UserDaoHibernateImpl().createUsersTable();
       // new UserDaoHibernateImpl().dropUsersTable();
        //new UserDaoHibernateImpl().saveUser("Alex", "More", (byte) 2);
        // userDaoHibernate.createUsersTable();
        // userDaoJDBC.createUsersTable();
        // userDaoJDBC.saveUser(1,"Бро","Бра", (byte) 19);
        //userDaoJDBC.createUsersTable();
        //System.out.println(userDaoJDBC.getAllUsers());
        // userDaoJDBC.cleanUsersTable();
    }
}
