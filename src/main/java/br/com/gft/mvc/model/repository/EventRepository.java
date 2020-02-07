package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
