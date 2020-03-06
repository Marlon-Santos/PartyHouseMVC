package br.com.gft.mvc.apiController.dto;

import br.com.gft.mvc.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private Long id;
    private String login;
    private String name;
    private String role;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.role = user.getRole().getRole();
    }

    public static List<UserDto> converter(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
