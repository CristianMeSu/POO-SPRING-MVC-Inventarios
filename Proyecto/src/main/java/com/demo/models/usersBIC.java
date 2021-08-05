package com.demo.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table( name= "usersBIC" )
public class UsersBIC {
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

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
