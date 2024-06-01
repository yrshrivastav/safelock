package in.app.safelock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/credentials")
public class ManagerController {

    @RequestMapping("/manager")
    public String manager(){
        return "user/add-credential";
    }

    @PostMapping("/add")
    public String add(){
        return "user/add-credential";
    }

    @RequestMapping("/manage")
    public String manage(){
        return "user/credentials";
    }

    @PostMapping("/show")
    public String show(){
        return "user/credentials";
    }

}
