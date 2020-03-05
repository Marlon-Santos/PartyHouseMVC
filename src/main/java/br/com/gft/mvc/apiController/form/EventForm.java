package br.com.gft.mvc.apiController.form;

import br.com.gft.mvc.apiController.dto.EventDto;
import br.com.gft.mvc.enums.MusicStyle;
import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;
import br.com.gft.mvc.model.repository.EventRepository;
import br.com.gft.mvc.model.repository.PartyHouseRepository;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class EventForm {
    private String eventName;
    private Integer capacity;
    private Date date;
    private Double price;
    private String musicStyle;
    private String link;
    private String partyHouse;

    public Event converter(PartyHouseRepository partyHouseRepository) {
        return new Event(eventName, capacity, date, price, stingToMusicStyle(), link, stingToPartyHouse(partyHouseRepository));
    }

    public PartyHouse stingToPartyHouse(PartyHouseRepository partyHouseRepository) {
        Optional<PartyHouse> partyHouse = partyHouseRepository.findByNameIgnoreCase(this.partyHouse);
        if (partyHouse.isPresent()) {
            return partyHouse.get();
        }
        return null;
    }

    public MusicStyle stingToMusicStyle() {
        for (MusicStyle test : MusicStyle.values()) {
            if (test.getMusicStyle().toLowerCase().trim() == musicStyle.toLowerCase().trim()) {
                return test;
            }
        }
        return null;
    }

    public EventDto update(Long id, EventRepository eventRepository, PartyHouseRepository partyHouseRepository) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            event.get().setEventName(eventName);
            event.get().setCapacity(capacity);
            event.get().setDate(date);
            event.get().setPrice(price);
            event.get().setMusicStyle(stingToMusicStyle());
            event.get().setLink(link);
            event.get().setPartyHouse(stingToPartyHouse(partyHouseRepository));
            return new EventDto(event.get());
        }
        return null;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPartyHouse() {
        return partyHouse;
    }

    public void setPartyHouse(String partyHouse) {
        this.partyHouse = partyHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventForm eventForm = (EventForm) o;
        return Objects.equals(eventName, eventForm.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName);
    }


}
