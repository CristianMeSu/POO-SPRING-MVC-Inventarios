package com.demo.services;

import com.demo.models.Contacts;
import com.demo.models.Users;
import com.demo.repositorio.IContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Contacts usuarios() {
        Contacts l = new Contacts();
        Users u = new Users();

        return l ;
    }

    public String getemail() {
        Contacts l= new Contacts();
        l.setEmail("cristian.mendoza.026@gmail.com");
        return l.getEmail();
    }

    public List getemailFromDB() {
        return contactsRepository.findAll();
    }
}
