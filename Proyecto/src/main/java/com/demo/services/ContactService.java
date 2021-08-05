package com.demo.services;

import com.demo.models.UsersBIC;
import com.demo.models.ContactsBIC;
import com.demo.repositorio.IContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    IContactsRepository contactsRepository;

    public List getList(){
        List l = new ArrayList();
        l.add("primero");
        l.add("segundo");
        l.add("tercero");

        return l ;
    }

    public UsersBIC usuarios() {
        UsersBIC l = new UsersBIC();
        ContactsBIC u = new ContactsBIC();

        return l ;
    }

    public String getemail() {
        ContactsBIC l= new ContactsBIC();
        l.setEmail("cristian.mendoza.026@gmail.com");
        return l.getEmail();
    }

    public List getemailFromDB() {
        return contactsRepository.findAll();
    }
    public Optional<UsersBIC> getUser(Long id) {
        return contactsRepository.findAllById(id);
    }
}
