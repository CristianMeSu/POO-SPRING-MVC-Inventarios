package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table( name= "contactsBIC" )
public class ContactsBIC {
    @Id
    @NotNull
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size( max=20)
    private String name;

    @NotNull
    @NotBlank
    @Size( max=32 )
    private String password;

    @NotNull
    @Size( max=75 )
    private String email;

    @NotNull
    @NotBlank
    @Size( max=20)
    private String phone;

    @NotNull
    @NotBlank
    @Size( max=20)
    private String rol;

    @NotNull
    private LocalDate dateCreated = LocalDate.now();

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
