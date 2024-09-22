package br.edu.pweb2.incruise.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.pweb2.incruise.model.exception.ItemNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ItemNotFoundException.class)
    public String  handleCustomException(Exception ex) {
        return "redirect:/system/error";

    }
}
