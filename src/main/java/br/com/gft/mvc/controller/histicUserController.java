package br.com.gft.mvc.controller;

import br.com.gft.mvc.model.entity.HistoricUser;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.HistoricUserRepository;
import br.com.gft.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class histicUserController {
    @Autowired
    private HistoricUserRepository historicUserRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/historic")
    public ModelAndView historic(@AuthenticationPrincipal User usuario, RedirectAttributes model){
        ModelAndView mv = new ModelAndView("historic");
//        User user = userRepository.findByLogin(usuario.getLogin());
//        List<HistoricUser> historicUsers =historicUserRepository.findAll();
//        mv.addObject("historic",historicUsers);
        return mv;
    }
    @PostMapping("/historic")
    public RedirectView hisricInclude(HistoricUser historicUser, @AuthenticationPrincipal User usuario, RedirectAttributes model){
        User user = userRepository.findByLogin(usuario.getLogin());
        historicUser.setUser(user);
        historicUserRepository.save(historicUser);
        return new RedirectView("http://localhost:8080/historic");
    }
}
