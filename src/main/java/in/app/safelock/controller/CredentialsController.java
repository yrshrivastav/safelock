package in.app.safelock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import in.app.safelock.entities.Credential;
import in.app.safelock.entities.User;
import in.app.safelock.forms.CredentialsForm;
import in.app.safelock.services.CredentialsService;
import in.app.safelock.services.UserService;
import in.app.safelock.support.Message;
import in.app.safelock.support.MessageType;
import in.app.safelock.support.Support;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/credentials")
public class CredentialsController {

    @Autowired
    private CredentialsService credentialsservice;

    @Autowired
    private UserService userservice;

    @GetMapping("/add")
    public String manager(Model model) {
        CredentialsForm credentialsForm = new CredentialsForm();
        model.addAttribute("credentialsForm", credentialsForm);
        return "user/add-credential";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute CredentialsForm credentialsForm,
            Authentication authentication, HttpSession session) {
        String email = Support.getEmailOfLoggedInUser(authentication);
        User user = userservice.getUserByEmail(email);

        Credential credential = new Credential();

        credential.setServiceName(credentialsForm.getServiceName());
        credential.setUsername(credentialsForm.getUsername());
        credential.setEmail(credentialsForm.getEmail());
        credential.setPassword(credentialsForm.getPassword());
        credential.setNotes(credentialsForm.getNotes());
        credential.setUser(user);
        credentialsservice.save(credential);

        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new credential")
                        .type(MessageType.green)
                        .build());

        System.out.println(credentialsservice);
        return "redirect:add";
    }

    @GetMapping("/manage")
    public String manage() {
        return "user/credentials";
    }

    @PostMapping("/manage")
    public String show() {
        return "redirect:user/credentials";
    }

}
