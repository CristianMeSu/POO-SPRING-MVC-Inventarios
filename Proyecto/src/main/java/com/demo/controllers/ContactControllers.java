package com.demo.controllers;



import com.demo.models.UsersBIC;
import com.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value ="/contactsBIC")
public class ContactControllers {

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public List HelloWorld(){
    return contactService.getList();


    }
    @RequestMapping(value = "/user", method= RequestMethod.GET)
    public UsersBIC usuario(){

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
    @RequestMapping(value = "/get-contact/{id}")
    public Optional<UsersBIC> getUser(@PathVariable Long id){

        return contactService.getUser(id);
    }
}


