package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users(ID BIGINT,NAME VARCHAR(128),LASTNAME VARCHAR(128),AGE SMALLINT)").executeUpdate();
            System.out.println("Table is created");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            System.out.println("Table is drop");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User is write");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(long id, String name, String lastname, byte age) {
        try (Session session = Util.getSession().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(new User(id,name,lastname,age));
            System.out.println("User is write");
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            System.out.println("Object is removed");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession().openSession()){
            Transaction transaction = session.beginTransaction();
            List list = session.createCriteria(User.class).list();
            transaction.commit();
            return list;
        }catch (HibernateException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession().openSession()){
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            System.out.println("Table is clean");
            session.getTransaction().commit();
        }catch (HibernateException e)
        {
            e.printStackTrace();
        }
    }

}
