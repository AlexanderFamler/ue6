package main.java.at.jku.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RestServiceApplication implements CommandLineRunner {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RestServiceApplication(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE orders IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE orders(" +
                "orderId SERIAL, handlebarType VARCHAR(255),handlebarMaterial VARCHAR(255)," +
                "handlebarGearshift VARCHAR(255),handleMaterial VARCHAR(255),deliveryDate DATE)");
    }
}
