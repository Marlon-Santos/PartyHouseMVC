package br.com.gft.mvc.apiController.dto;

import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PartyHouseDto {
    private Long id;
    private String name;
    private String address;
    private List<String> event;

    public PartyHouseDto(PartyHouse partyHouse) {
        this.id = partyHouse.getId();
        this.name = partyHouse.getName();
        this.address = partyHouse.getAddress();
        if (partyHouse.getEvent() != null) {
            this.event = stringListConverter(partyHouse.getEvent());
        }
    }

    private List<String> stringListConverter(List<Event> eventList) {
        return eventList.stream().map(Event::getEventName).collect(Collectors.toList());
    }

    public static List<PartyHouseDto> converter(List<PartyHouse> partyHouses) {
        return partyHouses.stream().map(PartyHouseDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getEvent() {
        return event;
    }

    public void setEvent(List<String> event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartyHouseDto that = (PartyHouseDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
