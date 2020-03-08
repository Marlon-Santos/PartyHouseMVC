package br.com.gft.mvc.apiController.dto;

import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.Ticket;
import br.com.gft.mvc.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HistoricDto {
    private Long id;
    private LocalDateTime time;
    private String user;
    private Long quantity;
    private String event;

    public HistoricDto(Ticket ticket) {
        this.id = ticket.getId();
        this.time = ticket.getTime();
        this.user = (ticket.getUser() == null) ? "Random User" : ticket.getUser().getName();
        this.quantity = ticket.getQuantity();
        this.event = ticket.getEvent().getEventName();
    }

    public HistoricDto() {
    }

    static public List<HistoricDto> converter (List<Ticket> ticket){
        return ticket.stream().map(HistoricDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricDto that = (HistoricDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}