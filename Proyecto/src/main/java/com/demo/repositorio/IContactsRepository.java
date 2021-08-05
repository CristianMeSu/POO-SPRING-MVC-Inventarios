package com.demo.repositorio;

import com.demo.models.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactsRepository extends JpaRepository <Contacts , Long> {

}
