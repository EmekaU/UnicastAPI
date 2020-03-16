package com.group.model;

import java.util.HashMap;
import java.util.Map;

public class UserTokenStore {

    private Map<String, String> secrets;

    public UserTokenStore(){
        this.secrets = new HashMap<String, String>();
    }

    public String getSecret(String username){
        return this.secrets.get(username);
    }

    public void addSecret(String username, String secret){
        try{
            this.secrets.put(username, secret);
        }
        catch(Exception e){
            System.out.println("Secret with username" +username+ " already Exists");
        }
    }
}
