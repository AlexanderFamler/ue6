import at.jku.restservice.HandlebarConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.List;

@RestController
public class RestApi{



    @GetMapping("/order") public ResponseEntity<HandlebarConfig> getLieferung()
    {


       // return availableHandlebarTypes;
    }
}
