package com.group.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private String message;

    private JSONObject body;

    private boolean success;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void addToBody(String key, String value){

        this.body.append(key, value);
    }

    public void addToBody(String key, JSONObject value){

        this.body.append(key, value);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
