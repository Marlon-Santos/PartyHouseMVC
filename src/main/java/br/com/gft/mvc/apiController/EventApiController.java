package br.com.gft.mvc.apiController;

import br.com.gft.mvc.apiController.dto.EventDto;
import br.com.gft.mvc.apiController.form.EventForm;
import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.PartyHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/eventos")
public class EventApiController {
    @Autowired
    private PartyHouseRepository partyHouseRepository;
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<EventDto> eventsList() {
        List<Event> events = eventRepository.findAll();
        return EventDto.converter(events);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EventDto> save(@RequestBody @Valid EventForm eventForm, UriComponentsBuilder uriBuilder) throws Exception {
        Event event = eventForm.converter(partyHouseRepository);
        eventRepository.save(event);
        URI uri = uriBuilder.path("{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(uri).body(new EventDto(event));
    }

    @GetMapping("{id}")
    public EventDto findById(@PathVariable Long id) throws Exception {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return new EventDto(event.get());
        } else {
            throw new Exception("id nao encontrado");
        }
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<EventDto> update(@PathVariable Long id, @RequestBody @Valid EventForm eventForm) throws Exception {
        EventDto eventDto = eventForm.update(id, eventRepository, partyHouseRepository);
        return ResponseEntity.ok(eventDto);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            eventRepository.delete(event.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{name}")
    public EventDto findByName(@PathVariable String name) throws Exception {
        Optional<Event> event = eventRepository.findByEventNameIgnoreCase(name);
        if (event.isPresent()) {
            return new EventDto(event.get());
        } else {
            throw new Exception("nome nao encontrado");
        }
    }

    @GetMapping("/capacidade/asc")
    public List<EventDto> findAllByCapacityAsc() {
        List<Event> events = eventRepository.findAllByOrderByCapacityAsc();
        return EventDto.converter(events);
    }

    @GetMapping("/capacidade/desc")
    public List<EventDto> findAllByCapacityDesc() {
        List<Event> events = eventRepository.findAllByOrderByCapacityDesc();
        return EventDto.converter(events);
    }

    @GetMapping("/data/asc")
    public List<EventDto> findAllByDateAsc() {
        List<Event> events = eventRepository.findAllByOrderByDateAsc();
        return EventDto.converter(events);
    }

    @GetMapping("/data/desc")
    public List<EventDto> findAllByDateDesc() {
        List<Event> events = eventRepository.findAllByOrderByDateDesc();
        return EventDto.converter(events);
    }

    @GetMapping("/nome/asc")
    public List<EventDto> findAllByEventNameAsc() {
        List<Event> events = eventRepository.findAllByOrderByEventNameAsc();
        return EventDto.converter(events);
    }

    @GetMapping("/nome/desc")
    public List<EventDto> findAllByEventNameDesc() {
        List<Event> events = eventRepository.findAllByOrderByEventNameDesc();
        return EventDto.converter(events);
    }

    @GetMapping("/preco/asc")
    public List<EventDto> findAllByPriceAsc() {
        List<Event> events = eventRepository.findAllByOrderByPriceAsc();
        return EventDto.converter(events);
    }

    @GetMapping("/preco/desc")
    public List<EventDto> findAllByPriceDesc() {
        List<Event> events = eventRepository.findAllByOrderByPriceDesc();
        return EventDto.converter(events);
    }


}
