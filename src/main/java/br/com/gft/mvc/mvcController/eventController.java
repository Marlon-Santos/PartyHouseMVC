package br.com.gft.mvc.mvcController;

import br.com.gft.mvc.enums.MusicStyle;
import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping("/event")
public class eventController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PartyHouseRepository partyHouseRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("event");
        return mv;
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("eventEdit");
        Optional<Event> event = eventRepository.findById(id);
        event.ifPresent(event1 -> {
            mv.addObject("id",id);
            mv.addObject("event",event1);
        });
        return mv;
    }

    @GetMapping("del/{id}")
    public RedirectView delete(@PathVariable Long id){
        eventRepository.delete(eventRepository.findById(id).get());
        return new RedirectView("http://localhost:8080/event");
    }

    @PostMapping
    public RedirectView save(Event event)  {
        eventRepository.save(event);
       return new RedirectView("http://localhost:8080/event");
    }
    @PostMapping("{id}")
    public RedirectView edit(@PathVariable Long id, Event event) {
        event.setId(id);
        eventRepository.save(event);
        return new RedirectView("http://localhost:8080/event");
    }

    @ModelAttribute("musicStyle")
    public List<MusicStyle> musicStyle() {
        return Arrays.asList(MusicStyle.values());
    }
    @ModelAttribute("partyHouse")
    public List<PartyHouse> partyHouse() {
        List<PartyHouse> name = new ArrayList<>() ;
        partyHouseRepository.findAll().forEach(partyHouse-> name.add(partyHouse));
         return name;
    }
    @ModelAttribute("events")
    public List<Event> events() {
        return eventRepository.findAll();
    }
    @ModelAttribute("hasHouse")
    public List<PartyHouse> partyHouses() {
        return partyHouseRepository.findAll();
    }
}