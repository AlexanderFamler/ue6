package main.java.at.jku.restservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@RestController public class HandlebarConfigController {


    private final JdbcTemplate jdbcTemplate;



    public static final String RENNRADLENKER = "Rennradlenker";
    public static final String FLATBARLENKER = "Flatbarlenker";
    public static final String ALUMINIUM = "Aluminium";
    public static final String KUNSTSTOFF = "Kunststoff";
    public static final String STAHL = "Stahl";
    public static final String KETTENSCHALTUNG = "Kettenschaltung";
    public static final String KUNSTSTOFFGRIFF = "Kunststoffgriff";
    public static final String LEDERGRIFF = "Ledergriff";
    private final List<String> availableHandlebarTypes;
    private final List<String> availableMaterial;
    private final List<String> availableGearshifts;
    private final List<String> availableHandleMaterial;

    @Autowired
    public HandlebarConfigController(final JdbcTemplate jdbcTemplate) {
        availableHandlebarTypes = new ArrayList<>();
        availableHandlebarTypes.add(FLATBARLENKER);
        availableHandlebarTypes.add(RENNRADLENKER);
        availableHandlebarTypes.add("Bullhornlenker");

        availableMaterial = new ArrayList<>();
        availableMaterial.add(ALUMINIUM);
        availableMaterial.add(STAHL);
        availableMaterial.add(KUNSTSTOFF);

        availableGearshifts = new ArrayList<>();
        availableGearshifts.add(KETTENSCHALTUNG);
        availableGearshifts.add("Nabenschaltung");
        availableGearshifts.add("Tretlagerschaltung");

        availableHandleMaterial = new ArrayList<>();
        availableHandleMaterial.add(LEDERGRIFF);
        availableHandleMaterial.add("Schaumstoffgriff");
        availableHandleMaterial.add(KUNSTSTOFFGRIFF);

        this.jdbcTemplate = jdbcTemplate;

    }

    @PostMapping("/order/{1}/{2}/{3}/{4}")
    public ResponseEntity<HandlebarConfig> order(@PathVariable("1") final String handlebarType,
                                                                              @PathVariable("2") final String handlebarMaterial,
                                                                              @PathVariable("3") final String handlebarGearshift,
                                                                              @PathVariable("4") final String handleMaterial) throws SQLException {

        if (!availableHandlebarTypes.contains(handlebarType)) {
            return getBadRequestResponseEntity(handlebarType, handlebarMaterial, handlebarGearshift,
                handleMaterial,
                "Configuration wrong: Type of Handlebar " + handlebarType + " is not available.");
        }
        if (!availableMaterial.contains(handlebarMaterial)) {
            return getBadRequestResponseEntity(handlebarType, handlebarMaterial, handlebarGearshift,
                handleMaterial,
                "Configuration wrong: Material " + handlebarMaterial + " is not available.");
        }
        if (!availableGearshifts.contains(handlebarGearshift)) {
            return getBadRequestResponseEntity(handlebarType, handlebarMaterial, handlebarGearshift,
                handleMaterial, "Configuration wrong: Type of gearshift " + handlebarGearshift
                    + " is not available.");
        }
        if (!availableHandleMaterial.contains(handleMaterial)) {
            return getBadRequestResponseEntity(handlebarType, handlebarMaterial, handlebarGearshift,
                handleMaterial,
                "Configuration wrong: Type of handle " + handleMaterial + " is not available.");
        }
        if (RENNRADLENKER.equalsIgnoreCase(handlebarType)) {
            if (!ALUMINIUM.equalsIgnoreCase(handlebarMaterial) && !KUNSTSTOFF
                .equalsIgnoreCase(handlebarMaterial)) {
                return getBadRequestResponseEntity(handlebarType, handlebarMaterial,
                    handlebarGearshift, handleMaterial,
                    "Configuration invalid: " + handlebarType + " is not compatible with material "
                        + handlebarMaterial);
            }
        }
        if (FLATBARLENKER.equalsIgnoreCase(handlebarType)) {
            if (!ALUMINIUM.equalsIgnoreCase(handlebarMaterial) && !KUNSTSTOFF
                .equalsIgnoreCase(handlebarMaterial)) {
                return getBadRequestResponseEntity(handlebarType, handlebarMaterial,
                    handlebarGearshift, handleMaterial,
                    "Configuration invalid: " + handlebarType + " is not compatible with material "
                        + handlebarMaterial);
            }
        }
        if (STAHL.equalsIgnoreCase(handlebarMaterial)) {
            if (!KETTENSCHALTUNG.equalsIgnoreCase(handlebarGearshift)) {
                return getBadRequestResponseEntity(handlebarType, handlebarMaterial,
                    handlebarGearshift, handleMaterial,
                    "Configuration invalid: " + handlebarMaterial
                        + " is not compatible with gearshift " + handlebarGearshift);
            }
        }
        if (KUNSTSTOFFGRIFF.equalsIgnoreCase(handleMaterial)) {
            if (!KUNSTSTOFF.equalsIgnoreCase(handlebarMaterial)) {
                return getBadRequestResponseEntity(handlebarType, handlebarMaterial,
                    handlebarGearshift, handleMaterial, "Configuration invalid: " + handleMaterial
                        + " is not compatible with material of handlebar " + handlebarMaterial);
            }
        }
        if (LEDERGRIFF.equalsIgnoreCase(handleMaterial)) {
            if (!RENNRADLENKER.equalsIgnoreCase(handlebarType)) {
                return getBadRequestResponseEntity(handlebarType, handlebarMaterial,
                    handlebarGearshift, handleMaterial, "Configuration invalid: " + handleMaterial
                        + " is not compatible with material of handlebar " + handlebarMaterial);
            }
        }


        final Random rnd = new Random();
        long orderId = BigInteger.probablePrime(8, rnd).longValue();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date deliveryDate = new java.sql.Date(calendar.getTime().getTime());


        try{
            Connection con = Database.getConnection();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO orders(orderId, handlebarType, handlebarMaterial,handlebarGearshift, handleMaterial, deliveryDate) " +
                " VALUES  (?,?,?,?,?,?)");
            pstmt.setLong(1, orderId );
            pstmt.setString(2, handlebarType);
            pstmt.setString(3, handlebarMaterial);
            pstmt.setString(4, handlebarGearshift);
            pstmt.setString(5,handleMaterial );
            pstmt.setDate(6, deliveryDate);
            pstmt.executeUpdate();
        con.close();
        }catch(Exception e){ System.out.println(e);}

        //testing if item is in DB
        try{
            Connection con = Database.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from orders");
            while(rs.next())
                System.out.println(rs.getLong(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"" +
                        " "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getDate(2));
            con.close();

        }catch(Exception e){ System.out.println(e);}


        final HandlebarConfig handlebarConfig =
                new HandlebarConfig(orderId, handlebarType, handlebarMaterial,
                        handlebarGearshift, handleMaterial, deliveryDate);

        return ResponseEntity.ok(handlebarConfig);
    }

    private ResponseEntity getBadRequestResponseEntity(final String handlebarType,
        final String handlebarMaterial, final String handlebarGearshift,
        final String handleMaterial, final String errorMessage) {
        return new ResponseEntity(
            HttpStatus.BAD_REQUEST + " - " + errorMessage + " - " + handlebarType + " - "
                + handlebarMaterial + " - " + handlebarGearshift + " - " + handleMaterial,
            HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAvailableHandlebarTypes") public List<String> getAvailableHandlebarTypes() {
        return availableHandlebarTypes;
    }

    @GetMapping("/getAvailableMaterial")
    public List<String> getAvailableMaterial(@RequestParam final String handlebarType) {
        return availableMaterial;
    }

    @GetMapping("/getAvailableGearshifts")
    public List<String> getAvailableGearshifts(@RequestParam final String handlebarMaterial) {
        return availableGearshifts;
    }

    @GetMapping("/getAvailableHandleMaterial")
    public List<String> getAvailableHandleMaterial(@RequestParam final String handlebarType,
        @RequestParam final String handlebarMaterial) {
        return availableHandleMaterial;
    }
}
