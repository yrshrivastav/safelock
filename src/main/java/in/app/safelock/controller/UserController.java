package in.app.safelock.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import in.app.safelock.entities.User;
import in.app.safelock.services.UserService;
import in.app.safelock.support.Support;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile")
    public String userProfile(Model model,
            Authentication authentication) {
        String username = Support.getEmailOfLoggedInUser(authentication);

        User user = userservice.getUserByEmail(username);
    // adding for profile
    List<User> cred = userservice.findUserByEmail(user.getEmail()); 
    model.addAttribute("cred", cred);
        return "user/profile";
    }
    
}
