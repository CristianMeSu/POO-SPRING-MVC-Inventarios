package com.demo.models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table( name = "crm_users" )
public class Users {

    @Id
    @NotNull
    @GeneratedValue( strategy = GenerationType.IDENTITY )

    private Long id ;
    @NotNull
    @NotBlank
    @Size( max=20 )
    private String users ;
    @NotNull
    @NotBlank
    @Size( max=32 )
    private String password ;

}