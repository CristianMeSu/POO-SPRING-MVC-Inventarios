package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table( name= "crm_users" )
public class usersClase {
    @Id
    @NotNull
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size( max=25 )
    private String name;

    @NotNull
    @Size( max=75 )
    private String email;

    private String phone;

    @NotNull
    private LocalDate dateCreated = LocalDate.now();
}

