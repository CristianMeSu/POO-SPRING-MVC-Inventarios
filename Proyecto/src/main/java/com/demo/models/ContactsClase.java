package com.demo.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table( name= "crm_contacs" )
public class ContactsClase {
   @Id
   @NotNull
   @GeneratedValue( strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   @NotBlank
   @Size( max=20 )
   private String user;

   @NotNull
   @NotBlank
   @Size( max=32 )
   private String password;
}
