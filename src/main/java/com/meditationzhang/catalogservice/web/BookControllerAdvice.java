package com.meditationzhang.catalogservice.web;


import com.meditationzhang.catalogservice.domain.BookAlreadyExistsException;
import com.meditationzhang.catalogservice.domain.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(BookNotFoundException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String bookAlreadyExistsHandler(BookAlreadyExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleValidationException(
            MethodArgumentNotValidException ex
    ){
        var errors = new HashMap<String,String>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    if(error instanceof FieldError fieldError)
                        errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        return errors;
    }
}
