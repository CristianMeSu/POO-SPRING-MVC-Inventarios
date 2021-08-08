package com.demo.utility;

import com.demo.models.Administrator;

import java.util.List;

public class Register {

    public boolean isValidateRegister(List<Administrator> administrators, Administrator administrator) {
        boolean definition = true;
        for (Administrator adminIterable : administrators) {
            if (adminIterable.getUserName().equals(administrator.getUserName())) {
                definition = false;
                break;
            }
        }
        return definition;
    }
}
