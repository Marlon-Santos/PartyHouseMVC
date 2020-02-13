package br.com.gft.mvc.controller;

import br.com.gft.mvc.model.entity.User;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.LoginContext;


@Controller
public class loginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
