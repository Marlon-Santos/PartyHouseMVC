package br.com.gft.mvc.model.repository;

import br.com.gft.mvc.enums.Roles;
import br.com.gft.mvc.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLogin(String login);
    List<User> findAllByRole(Roles role);
}
