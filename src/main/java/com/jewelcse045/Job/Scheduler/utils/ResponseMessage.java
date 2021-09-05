package com.jewelcse045.Job.Scheduler.utils;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;


public class ResponseMessage {

    public static String errorResponse(Exception message, HttpStatus code) throws JSONException {
        JSONObject errorJSON=new JSONObject();

        errorJSON.put("success",false);
        errorJSON.put("message",message.getMessage());
        errorJSON.put("status_code",code.value());
        return errorJSON.toString();

    }

    public static String voidSuccessResponse(String message, HttpStatus code) throws JSONException {
        JSONObject errorJSON=new JSONObject();

        errorJSON.put("success",true);
        errorJSON.put("message",message);
        errorJSON.put("status_code",code.value());
        return errorJSON.toString();

    }
}
