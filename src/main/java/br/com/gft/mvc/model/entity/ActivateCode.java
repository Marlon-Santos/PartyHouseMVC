package br.com.gft.mvc.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
//import java.util.UUID;

@Entity
public class ActivateCode {
    @Id
    private String email;
    private String code;
    private String password;
    private String name;
    public String getEmail() {
        return email;
    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return Objects.hash(email);
    }
}
