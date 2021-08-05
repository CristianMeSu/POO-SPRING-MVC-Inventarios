package com.demo.controllers;



import com.demo.models.Contacts;
import com.demo.models.Users;
import com.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value ="/contacts")
public class ContactControllers {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public List HelloWorld(){
    return contactService.getList();


    }
    @RequestMapping(value = "/user", method= RequestMethod.GET)
    public Contacts usuario(){

    return contactService.usuarios();
    }

    @RequestMapping(value = "/email", method= RequestMethod.GET)
    public String getEmail(){

        return contactService.getemail();
    }
    @RequestMapping(value = "/db-list", method= RequestMethod.GET)
    public List getList(){

        return contactService.getemailFromDB();
    }

    }

