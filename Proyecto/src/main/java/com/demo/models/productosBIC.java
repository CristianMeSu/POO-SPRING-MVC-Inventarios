package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name= "productosBIC" )
public class productosBIC {
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
    private Long cantidad;

    @NotNull
    @Size( max=20 )
    private String categoria;

    @NotNull
    @NotBlank
    private Long precio;

    @Size( max=255)
    private String descripcion;
}
