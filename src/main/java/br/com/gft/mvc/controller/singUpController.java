package br.com.gft.mvc.controller;

import br.com.gft.mvc.model.entity.ActivateCode;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.CodeRepository;
import br.com.gft.mvc.model.repository.UserRepository;
import br.com.gft.mvc.service.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.util.UUID;

@Controller
public class singUpController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private CodeRepository codeRepository;

    @GetMapping("/singUp")
    public ModelAndView user() {
        ModelAndView mv = new ModelAndView("singUp");
        return mv;
    }

    @Transactional
    @PostMapping("/singUp")
    public ModelAndView newUser(User user, ActivateCode activateCode) {
        //implementar add ADMIN pimeiro Acesso
//        userRepository.findAllByRole(Roles.ADMIN).forEach(item -> System.out.println(item.getLogin() + ">>>>>>>>>" + item.getRole()));
        //fazer validacao c ja nao existe

        UUID id = UUID.randomUUID();
        SendEmail sendEmail = new SendEmail(
                user.getLogin(),
                "party house manager code activation",
                "code to active your account: " + id,
                javaMailSender
        );
        String bCrypt = new BCryptPasswordEncoder().encode(user.getPassword());
        activateCode.setEmail(user.getLogin());
        activateCode.setPassword(bCrypt);
        activateCode.setCode(id.toString());
        activateCode.setName(user.getName());
        codeRepository.save(activateCode);

        ModelAndView mv = new ModelAndView("codVerificator");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/codVerificator")
    public ModelAndView isValid(String email, String code, User user) {
        ActivateCode activateCode = codeRepository.findByEmail(email);
        System.out.println(">>>>>>>>>>>>>>>>>>>.."+activateCode.getCode()+"=="+ code+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (activateCode.getCode().trim().equals(code.trim()) ){
            user.setLogin(activateCode.getEmail());
            user.setName(activateCode.getName());
            user.setPassword(activateCode.getPassword());
            userRepository.save(user);
            System.out.println("code is valid!!!!");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/codVerificator.html");
            mv.addObject("user", user);
//            return new RedirectView("http://localhost:8080/codVerificator");
//             new ModelMap("user",user);
             return mv;
        }
        System.out.println("entrouuuuuuuuuuuuuuuuuuuuuuuuu");
        return new ModelAndView("redirect:/codVerificator.html");
//        return "redirect:/login";
    }
}
