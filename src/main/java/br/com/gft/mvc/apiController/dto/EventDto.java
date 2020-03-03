package br.com.gft.mvc.apiController.dto;

import br.com.gft.mvc.model.entity.Event;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EventDto {
    private Long id;
    private String eventName;
    private Integer capacity;
    private Date date;
    private Double price;
    private String musicStyle;
    private String link;
    private String partyHouse;

    public EventDto(Event event) {
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.capacity = event.getCapacity();
        this.date = event.getDate();
        this.price = event.getPrice();
        this.musicStyle = event.getMusicStyle().getMusicStyle();
        this.link = event.getLink();
        this.partyHouse = event.getPartyHouse().getName();
    }

    public static List<EventDto> converter(List<Event> events) {
        return events.stream().map(EventDto::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
