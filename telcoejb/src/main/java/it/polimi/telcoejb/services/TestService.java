package it.polimi.telcoejb.services;

import jakarta.ejb.Stateless;

@Stateless
public class TestService {

    public String getMessage(){
        return "Hello "+Math.random()*10;
    }

}
