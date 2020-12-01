package restapi2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RestConfig2
{

    public RestConfig2(){}
    @GetMapping("/order")
    public String order(@PathVariable("1") final String handlebarType,
                        @PathVariable("2") final String handlebarMaterial,
                        @PathVariable("3") final String handlebarGearshift,
                        @PathVariable("4") final String handleMaterial)
    {

        return handlebarType+handlebarMaterial+handlebarGearshift+handleMaterial;
    }

    public String calculateDatePrice()
    {
        List a = new ArrayList<>();

        Random r = new Random();
        int low = 120;
        int high = 400;
        int result = r.nextInt(high-low) + low;

        System.out.println(result);

        LocalDate startDate = LocalDate.now();
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.of(2021, 12, 31);
        long end = endDate.toEpochDay();


        long randomEpochDay =  ThreadLocalRandom.current().nextLong(start, end);
        System.out.println(LocalDate.ofEpochDay(randomEpochDay));

        return ""+result + LocalDate.ofEpochDay(randomEpochDay);
    }
}
