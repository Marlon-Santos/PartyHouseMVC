package br.com.gft.mvc.mvcController;

import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.Ticket;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.TicketRepository;
import br.com.gft.mvc.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class homeController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        List<Event> event = eventRepository.findAll();
        mv.addObject("events", event);
        return mv;
    }

    @PostMapping("/sales")
    public String sales(Long id, int qtd) {
        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(event1 -> {
            if (qtd > 0) {
                if (event1.getCapacity() - qtd >= 0) {
                    Ticket ticket = new Ticket(event1, (long) qtd, userRepository);
                    event1.setCapacity(event1.getCapacity() - qtd);
                    eventRepository.save(event1);
                    ticketRepository.save(ticket);
                }
            }
        });
        return "redirect:/";
    }

}
