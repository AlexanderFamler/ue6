package src.restapi;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RestConfig
{

    private int price;
    private LocalDate date;

    public RestConfig(int price, LocalDate date){
        this.price = price;
        this.date =date;
    }

    /*@GetMapping("/order")
    public String order(@PathVariable("1") final String handlebarType,
                                                 @PathVariable("2") final String handlebarMaterial,
                                                 @PathVariable("3") final String handlebarGearshift,
                                                 @PathVariable("4") final String handleMaterial)
    {

        return handlebarType+handlebarMaterial+handlebarGearshift+handleMaterial;
    }*/


    public List<RestConfig> calculateDatePrice()
    {
        //List<String> datePrice = new ArrayList<>();

        List<RestConfig> datePrice = new ArrayList<>();

        Random r = new Random();
        int low = 120;
        int high = 400;
        int result = r.nextInt(high-low) + low;

        //datePrice.add(""+result);

        LocalDate startDate = LocalDate.now();
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.of(2021, 12, 31);
        long end = endDate.toEpochDay();


        long randomEpochDay =  ThreadLocalRandom.current().nextLong(start, end);

        //datePrice.add(""+LocalDate.ofEpochDay(randomEpochDay));



        datePrice.add(new RestConfig(result, LocalDate.ofEpochDay(randomEpochDay)));

        return datePrice;

        //return datePrice;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }
}
