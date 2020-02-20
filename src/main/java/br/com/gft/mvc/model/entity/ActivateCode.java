package br.com.gft.mvc.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
//import java.util.UUID;

@Entity
public class ActivateCode {
    @Id
    private String login;
    private String code;
    private String password;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivateCode that = (ActivateCode) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}

