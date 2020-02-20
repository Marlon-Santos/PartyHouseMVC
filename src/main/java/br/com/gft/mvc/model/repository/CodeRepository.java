package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.ActivateCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<ActivateCode,String> {
    ActivateCode findByEmail(String email);
}
