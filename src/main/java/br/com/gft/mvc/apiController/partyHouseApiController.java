package br.com.gft.mvc.apiController;


import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/casas")
public class partyHouseApiController {
    @Autowired
    private PartyHouseRepository partyHouseRepository;

    @GetMapping
    public List<PartyHouse> partyHouseList(){
        List<PartyHouse> partyHouseList = partyHouseRepository.findAll();
        return partyHouseList;
    }

    @PostMapping
    public RedirectView save(PartyHouse partyHouse){
        partyHouseRepository.save(partyHouse);
        return new RedirectView("http://localhost:8080/casaDeShow");
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
    @GetMapping("del/{id}")
    public RedirectView delete(@PathVariable Long id){
        partyHouseRepository.delete(partyHouseRepository.findById(id).get());
        return new RedirectView("http://localhost:8080/casaDeShow");
    }

    @PostMapping("{id}")
    public RedirectView update(@PathVariable Long id, PartyHouse partyHouse){
        partyHouse.setId(id);
        partyHouseRepository.save(partyHouse);
        return new RedirectView("http://localhost:8080/casaDeShow");
    }
    @ModelAttribute("houses")
    public List<PartyHouse> houses(){
        List<PartyHouse> partyHouse = partyHouseRepository.findAll();
        return partyHouse;
    }

}
