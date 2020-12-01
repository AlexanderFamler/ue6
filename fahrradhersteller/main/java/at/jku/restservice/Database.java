package main.java.at.jku.restservice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    public static void main(String args[]){
        try{
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root","#rentWare");

//here db is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from orders");
            while(rs.next())
                System.out.println(rs.getLong(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"" +
                        " "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getDate(2));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

}