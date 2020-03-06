package br.com.gft.mvc.mvcController;

import br.com.gft.mvc.model.entity.Ticket;
import br.com.gft.mvc.model.entity.User;
import br.com.gft.mvc.model.repository.TicketRepository;
import br.com.gft.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/historic")
public class HistoricController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView historic() {
        ModelAndView mv = new ModelAndView("historic");
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByName(name);
        List<Ticket> tickets = ticketRepository.findAllByUser(user);
        mv.addObject("tickets", tickets);
        return mv;
    }
}
