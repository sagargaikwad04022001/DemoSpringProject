package com.mongo.demo.exception;

import com.mongo.demo.response.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> catchIdNotFound(IdNotFoundException exception) {
        ResponseStructure rs = new ResponseStructure();
        rs.setMsg("Id Not Exist");
        rs.setData(exception.getMessage());
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
    }

}
