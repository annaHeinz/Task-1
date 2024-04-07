package jm.task.core.jdbc.util;

//import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
}
