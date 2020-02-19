package br.com.gft.mvc.controller;

import br.com.gft.mvc.enums.Roles;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.UserRepository;
import br.com.gft.mvc.service.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class singUpController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/singUp")
    public ModelAndView user() {
        ModelAndView mv = new ModelAndView("singUp");
        return mv;
    }

    @PostMapping("/singUp")
    public ModelAndView newUser(User user) {
        //implementar add ADMIN pimeiro Acesso
//        userRepository.findAllByRole(Roles.ADMIN).forEach(item -> System.out.println(item.getLogin() + ">>>>>>>>>" + item.getRole()));
        //fazer validacao c ja nao existe
        UUID id = UUID.randomUUID();

        SendEmail sendEmail = new SendEmail(
             user.getLogin(),
             "party house manager code activation",
               "code to active your account: " + id,
               javaMailSender);
        ModelAndView mv = new ModelAndView("codVerificator");
        mv.addObject("user",user);
        mv.addObject("cod",id);
        return mv;
    }

    @PostMapping("/codVerificator")
    public ModelAndView isValid(User user) {
        ModelAndView mv = new ModelAndView("codVerificator");
        String bCrypt = new BCryptPasswordEncoder().encode(user.getPassword());
        System.out.println(bCrypt + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user.getLogin());
        user.setPassword(bCrypt);
        userRepository.save(user);
        return mv;
    }
}
