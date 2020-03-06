package br.com.gft.mvc.model.entity;

import br.com.gft.mvc.enums.MusicStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    @NotNull
    private String eventName;
    @NotNull
    @Min(value = 0,message = "not negative number")
    private Integer capacity;
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @NotNull
    @NumberFormat(pattern = "#,##0.00")
    private Double price;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MusicStyle musicStyle;
    private String link;
    @NotNull
    @ManyToOne
    private PartyHouse partyHouse;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    public Event() {
    }

    public Event(String eventName, Integer capacity, Date date, Double price, MusicStyle musicStyle, String link, PartyHouse partyHouse) {
        this.eventName = eventName;
        this.capacity = capacity;
        this.date = date;
        this.price = price;
        this.musicStyle = musicStyle;
        this.link = link;
        this.partyHouse = partyHouse;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(MusicStyle musicStyle) {
        this.musicStyle = musicStyle;
    }

    public Long getId() {
        return id;
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

    public String getLink() {
        return link;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setLink(String link) {
        this.link = link;
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

    public PartyHouse getPartyHouse() {
        return partyHouse;
    }

    public void setPartyHouse(PartyHouse partyHouse) {
        this.partyHouse = partyHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}