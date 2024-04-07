package jm.task.core.jdbc.util;

//import com.mysql.cj.jdbc.ConnectionImpl;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import javax.security.auth.login.AppConfigurationEntry;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        String url="jdbc:postgresql://localhost:5432/postgres";
        String name="postgres";
        String password="postgres";
        Connection conn=null;
        try{
            conn= DriverManager.getConnection(url,name,password);
            System.out.println("Succesful connection");
        }
        catch (SQLException e){e.printStackTrace();}
        return conn;
    }

    public static SessionFactory getSessionFactory(){
        return new  Configuration().addAnnotatedClass(User.class).buildSessionFactory();

    }
}
