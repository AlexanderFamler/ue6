package main.java.at.jku.restservice;
import java.sql.Connection;
import java.sql.DriverManager;


public class Database {
    public static Connection con;

    static Connection getConnection() {
        {
            try
            { ;
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "#rentWare");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return con;
        }

    }
}
