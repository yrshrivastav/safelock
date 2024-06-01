package in.app.safelock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.app.safelock.entities.User;
import in.app.safelock.forms.UserForm;
import in.app.safelock.services.PasswordGenerator;
import in.app.safelock.services.UserService;
import in.app.safelock.support.Message;
import in.app.safelock.support.MessageType;
import jakarta.servlet.http.HttpSession;


@Controller
public class SafeLockController {

    @Autowired
    private PasswordGenerator passwordGenerator;

    @Autowired
    private UserService userService;

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

    // @PostMapping("/login")
    // public String loginPageProcess() {
    //     return "login";
    // }
    // @GetMapping("/login")
    // public String loginPage() {
    //     return "login";
    // }
    @GetMapping("/login")
    public String loginPage() {
        return new String("login");
    }

    @PostMapping("/register")
    public String registerPageProcess(@ModelAttribute UserForm userForm, HttpSession session) {
        System.out.println(userForm);

        // if (bindingResult.hasErrors()) {
        //     return "register";
        // }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNo(userForm.getPhoneNo());
        user.setProfilePic("static/images/default_user.jpg");

        User savedUser = userService.saveUser(user);

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);
        return "redirect:/register";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @GetMapping("/generator")
    public String generatorPage() {
        
        return "generator";
    }

    @PostMapping("/generator")
    public String passwordGenerator(@RequestParam(name = "length", required = true) Integer length, Model model) {
        if (length != null) {
            String password = passwordGenerator.generateRandomPassword(length);
            model.addAttribute("password", password);
        }
        return "generator";
    }
}
