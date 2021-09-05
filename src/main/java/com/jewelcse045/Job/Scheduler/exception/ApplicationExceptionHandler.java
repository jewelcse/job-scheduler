package com.jewelcse045.Job.Scheduler.exception;

import com.jewelcse045.Job.Scheduler.utils.ResponseMessage;
import org.codehaus.jettison.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value=ApplicationException.class)
    public ResponseEntity<?> applicationException(ApplicationException exception) throws JSONException {
        return new ResponseEntity<>(ResponseMessage.errorResponse(exception,HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=CompanyNotFoundException.class)
    public ResponseEntity<?> companyNotFoundException(CompanyNotFoundException exception) throws JSONException {
        return new ResponseEntity<>(ResponseMessage.errorResponse(exception,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=JobNotFoundException.class)
    public ResponseEntity<?> jobNotFoundException(JobNotFoundException exception) throws JSONException {
        return new ResponseEntity<>(ResponseMessage.errorResponse(exception,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value=TagNotFoundException.class)
    public ResponseEntity<?> tagNotFoundException(TagNotFoundException exception) throws JSONException {
        return new ResponseEntity<>(ResponseMessage.errorResponse(exception,HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

}
