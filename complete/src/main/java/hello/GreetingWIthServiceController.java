package hello;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingWIthServiceController {

    private final GreetingService service;

    public GreetingWIthServiceController(GreetingService service) {
        this.service = service;
    }

    @RequestMapping("/greetingWithService")
    public @ResponseBody String greeting() {
        return service.greet();
    }
}
