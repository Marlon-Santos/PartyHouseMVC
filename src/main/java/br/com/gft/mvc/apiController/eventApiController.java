package br.com.gft.mvc.apiController;

import br.com.gft.mvc.apiController.dto.EventDto;
import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
public class eventApiController {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PartyHouseRepository partyHouseRepository;

    @GetMapping
    public List<EventDto> eventList() {
        List<Event> events = eventRepository.findAll();
        return EventDto.converter(events);
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

}