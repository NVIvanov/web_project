package ru.web.project.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author nivanov
 *         on 03.11.16.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    //TODO mephi email regexp
    @Email
    private String email;

    @JsonIgnore
    private String password;
    @DateTimeFormat(pattern = "dd.MM.yy")
    private LocalDate lastLogin;
    @NotNull
    private Boolean admin;

    @OneToMany
    private Set<SiteDeveloper> developers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Set<SiteDeveloper> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<SiteDeveloper> developers) {
        this.developers = developers;
    }
}
