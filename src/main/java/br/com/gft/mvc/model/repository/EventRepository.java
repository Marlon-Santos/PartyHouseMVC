package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {

}
