package br.com.gft.mvc.model.repository;


import br.com.gft.mvc.model.entity.HistoricUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricUserRepository extends JpaRepository<HistoricUser,Long> {
}
