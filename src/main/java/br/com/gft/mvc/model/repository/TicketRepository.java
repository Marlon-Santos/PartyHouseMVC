package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
