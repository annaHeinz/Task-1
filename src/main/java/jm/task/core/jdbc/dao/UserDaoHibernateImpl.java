package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory;
    public UserDaoHibernateImpl() {
       sessionFactory= Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        String sql= "CREATE TABLE IF NOT EXISTS users(id bigserial primary key,name varchar(20) not  null,lastname varchar(20) not  null,age int not null)";
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.createNativeQuery(sql,User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql="DROP TABLE IF EXISTS users";
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.createNativeQuery(sql,User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction=null;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            User user=session.get(User.class,id);
            session.remove(user);
            session.getTransaction().commit();
        }catch (HibernateError e){
            if(transaction!=null){
                transaction.rollback();
            }
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList;
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            userList=session.createQuery("FROM User",User.class).getResultList();
            session.getTransaction().commit();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session=sessionFactory.openSession()){
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users",User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }
}
