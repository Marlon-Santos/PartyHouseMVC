package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.Event;
import br.com.gft.mvc.model.entity.PartyHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    public Optional<Event> findByEventNameIgnoreCase(String eventName);

    public List<Event> findAllByOrderByCapacityAsc();
    public List<Event> findAllByOrderByCapacityDesc();

    public List<Event> findAllByOrderByDateAsc();
    public List<Event> findAllByOrderByDateDesc();

    public List<Event> findAllByOrderByEventNameAsc();
    public List<Event> findAllByOrderByEventNameDesc();

    public List<Event> findAllByOrderByPriceAsc();
    public List<Event> findAllByOrderByPriceDesc();
}
