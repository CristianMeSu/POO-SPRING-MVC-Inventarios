package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table( name= "usersBIC" )
public class usersBIC {
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

}
