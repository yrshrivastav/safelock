package in.app.safelock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SafeLockController {

    

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    // @GetMapping("/services")
    // public String servicePage() {
    //     return "services";
    // }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/login")
    public String loginPageProcess() {
        return "login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String registerPageProcess() {
        return "register";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/generator")
    public String generatorPage() {
        return "generator";
    }
}
