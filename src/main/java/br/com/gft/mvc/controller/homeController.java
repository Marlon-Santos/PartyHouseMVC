package br.com.gft.mvc.controller;

import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class homeController {
    @Autowired
    private EventRepository eventRepository;
   @GetMapping("/")
    public ModelAndView home(){
       ModelAndView mv = new ModelAndView("home");
        List<Event> event = eventRepository.findAll();
        mv.addObject("events",event);
       return mv;
   }
   @GetMapping("{id}")
    public RedirectView compra(@PathVariable Long id){
      Optional<Event> event = eventRepository.findById(id);
      event.ifPresent(event1 -> {
          event1.setCapacity(event1.getCapacity()-1);
          eventRepository.save(event1);
      });
      return new RedirectView("http://localhost:8080");
   }
}
