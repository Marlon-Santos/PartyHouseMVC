package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
}
