package br.com.gft.mvc.controller;

import br.com.gft.mvc.enums.Roles;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.UserRepository;
import br.com.gft.mvc.service.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.LoginContext;


@Controller
public class loginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
