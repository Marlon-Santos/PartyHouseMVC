package br.com.gft.mvc.controller;


import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/casaDeShow")
public class partyHouseController {
    @Autowired
    private PartyHouseRepository partyHouseRepository;

    @GetMapping
    public String partyHouseView(){
        for(PartyHouse test : partyHouseRepository.findAll()){
            test.getEvent();
        }
        return "partyHouse";
    }

    @PostMapping
    public String save(PartyHouse partyHouse){
        partyHouseRepository.save(partyHouse);
        return "partyHouse";
    }

    @GetMapping("{id}")
    public ModelAndView update(@PathVariable Long id){
        Optional<PartyHouse> partyHouse = partyHouseRepository.findById(id);
        ModelAndView mv = new ModelAndView("partyHouseEdit");
        partyHouse.ifPresent(partyHouse1 -> {
            mv.addObject("partyHouse",partyHouse1);
            mv.addObject("id",id);
        });
        return mv;
    }
    @PostMapping("{id}")
    public RedirectView update(@PathVariable Long id, PartyHouse partyHouse){
        partyHouse.setId(id);
        System.out.println(">>>>>>>>>>>>>>>"+partyHouse.getId());
        partyHouseRepository.save(partyHouse);
        return new RedirectView("localhost:8080/event");
    }

    @ModelAttribute("eventlist")
    public List<String> test() {
        List<String> listEvent = new ArrayList<>();
        partyHouseRepository.findAll().get(0).getEvent().forEach(event->listEvent.add(event.getEventName()));
        listEvent.forEach(i-> System.out.println(i));
        return listEvent;
    }
}
