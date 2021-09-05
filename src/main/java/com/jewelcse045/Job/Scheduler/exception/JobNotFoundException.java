package com.jewelcse045.Job.Scheduler.exception;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(String message){
        super(message);
    }
}
