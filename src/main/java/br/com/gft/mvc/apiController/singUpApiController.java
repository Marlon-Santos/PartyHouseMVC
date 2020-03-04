package br.com.gft.mvc.apiController;

import br.com.gft.mvc.model.entity.ActivateCode;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.CodeRepository;
import br.com.gft.mvc.model.repository.UserRepository;
import br.com.gft.mvc.service.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class singUpApiController {
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
        activateCode.setLogin(user.getLogin());
        activateCode.setPassword(bCrypt);
        activateCode.setCode(id.toString());
        activateCode.setName(user.getName());
        codeRepository.save(activateCode);

        ModelAndView mv = new ModelAndView("codVerificator");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/codVerificator")
    public ModelAndView isValid(String login, String code, User user) {
        ActivateCode activateCode = codeRepository.findByLogin(login);
        System.out.println(">>>>>>>>>>>>>>>>>>>.."+activateCode.getCode()+"=="+ code+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (activateCode.getCode().trim().equals(code.trim()) ){
            user.setLogin(activateCode.getLogin());
            user.setName(activateCode.getName());
            user.setPassword(activateCode.getPassword());
            userRepository.save(user);
        } else {
            ModelAndView mv = new ModelAndView("codVerificator");
            mv.addObject("user", activateCode);
             return mv;
        }
        return new ModelAndView("redirect:/login.html");
    }
}
