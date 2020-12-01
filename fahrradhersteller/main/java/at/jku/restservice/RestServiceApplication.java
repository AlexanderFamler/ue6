package main.java.at.jku.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;

@SpringBootApplication
public class RestServiceApplication implements CommandLineRunner {


    private JdbcTemplate jdbcTemplate;



    @Autowired
    public RestServiceApplication(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(RestServiceApplication.class, args);
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root","#rentWare");
        System.out.println("connected");
        Statement stmt=con.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS orders");
        stmt.executeUpdate("CREATE TABLE orders(" +
                "orderId SERIAL, handlebarType VARCHAR(255),handlebarMaterial VARCHAR(255)," +
                "handlebarGearshift VARCHAR(255),handleMaterial VARCHAR(255),deliveryDate DATE)");
        con.close();
    }

    @Override
    public void run(final String... args) throws Exception {

    }
}
