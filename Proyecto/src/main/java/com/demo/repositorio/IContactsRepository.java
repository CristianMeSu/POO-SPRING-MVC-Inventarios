package com.demo.repositorio;

import com.demo.models.UsersBIC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IContactsRepository extends JpaRepository <UsersBIC, Long> {

    Optional<UsersBIC> findAllById(Long id);
}
