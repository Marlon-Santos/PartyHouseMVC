package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.Ticket;
import br.com.gft.mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findAllByUser(User user);
}
