package com.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table( name= "ventasBIC" )
public class ventasBIC {
    @Id
    @NotNull
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private Long iduser;

    @NotNull
    @NotBlank
    private Long idproducto;

    @NotNull
    @NotBlank
    private Long total;

    @NotNull
    @NotBlank
    private Date fecha;
}
