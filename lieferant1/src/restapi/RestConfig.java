package restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestConfig
{

    public RestConfig(){}
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

        
        return "";
    }
}
