package br.com.gft.mvc.mvcController;

import br.com.gft.mvc.enums.MusicStyle;
import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@SpringBootTest
public class eventControllerTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PartyHouseRepository partyHouseRepository;

//
//    @GetMapping
//    public ModelAndView home() {
//        ModelAndView mv = new ModelAndView("event");
//        return mv;
//    }
//
//    @GetMapping("{id}")
//    public ModelAndView edit(@PathVariable Long id) {
//        ModelAndView mv = new ModelAndView("eventEdit");
//        Optional<Event> event = eventRepository.findById(id);
//        event.ifPresent(event1 -> {
//            mv.addObject("id",id);
//            mv.addObject("event",event1);
//        });
//        return mv;
//    }
//
//    @GetMapping("del/{id}")
//    public String delete(@PathVariable Long id){
//        eventRepository.delete(eventRepository.findById(id).get());
//        return "redirect:/event";
//    }

    @Test
    public void testSave() { try {
//        PartyHouse partyHouse = new PartyHouse();
//        partyHouse.setName("tstando");
//        partyHouse.setAddress("testando");
//        partyHouse.setId(10L);
//        /////////////////////////////////////
//        Event test = new Event();
//        test.setEventName("test");
//        test.setMusicStyle(MusicStyle.AXE);
//        test.setPrice(100.00);
//        test.setDate(new Date());
//        test.setPartyHouse(partyHouse);
//        eventController ev = new eventController();
        eventController test = Mockito.mock(eventController.class);
        Mockito.when(test.save(new Event())).thenReturn(String.valueOf(true));
        Assertions.assertEquals(String.valueOf(true), test.save(new Event()));
//            System.out.println( test.save(new Event()));
//            ev.save(test);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }

//        eventRepository.save(event);
    }

    @Test
    public void edit(@PathVariable Long id, Event event) {
        event.setId(id);
        eventRepository.save(event);

    }

//    @ModelAttribute("musicStyle")
//    public List<MusicStyle> musicStyle() {
//        return Arrays.asList(MusicStyle.values());
//    }
//    @ModelAttribute("partyHouse")
//    public List<PartyHouse> partyHouse() {
//        List<PartyHouse> name = new ArrayList<>() ;
//        partyHouseRepository.findAll().forEach(partyHouse-> name.add(partyHouse));
//         return name;
//    }
//    @ModelAttribute("events")
//    public List<Event> events() {
//        return eventRepository.findAll();
//    }
//    @ModelAttribute("hasHouse")
//    public List<PartyHouse> partyHouses() {
//        return partyHouseRepository.findAll();
//    }
}