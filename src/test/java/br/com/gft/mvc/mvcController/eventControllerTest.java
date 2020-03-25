package br.com.gft.mvc.mvcController;

import br.com.gft.mvc.enums.MusicStyle;
import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@SpringBootTest
public class eventControllerTest {
    @Mock
    private EventRepository eventRepository;
    @Autowired
    private PartyHouseRepository partyHouseRepository;

    @Test
    public void testSave() {
        try {
            eventController test = Mockito.mock(eventController.class);
            Mockito.when(test.save(new Event())).thenReturn(String.valueOf(true));
            Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(new Event()));
            System.out.println(eventRepository.findById(1L).get().getId());
            Assertions.assertEquals(String.valueOf(true), test.save(new Event()));

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    public void edit(@PathVariable Long id, Event event) {
        event.setId(id);
        eventRepository.save(event);
    }
}