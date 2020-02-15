package br.com.gft.mvc.controller;

import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/loginCadastro")
    public ModelAndView user(){
        ModelAndView mv = new ModelAndView("loginCadastro");
        return mv;
    }
    @PostMapping("/loginCadastro")
    public RedirectView newUser(User user){
        String bCrypt = new BCryptPasswordEncoder().encode(user.getPassword());
        System.out.println(bCrypt+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+user.getLogin());
        user.setPassword(bCrypt);
        userRepository.save(user);
        return new RedirectView("http://localhost:8080/login");
    }
}
