package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.PartyHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartyHouseRepository extends JpaRepository<PartyHouse,Long> {
    public List<PartyHouse> findAllByOrderByNameAsc();

    public List<PartyHouse> findAllByOrderByNameDesc();

    public Optional<PartyHouse> findByNameIgnoreCase(String name);
}
