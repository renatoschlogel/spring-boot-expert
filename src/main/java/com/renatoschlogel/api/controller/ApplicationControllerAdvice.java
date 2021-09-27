package com.renatoschlogel.api.controller;

import com.renatoschlogel.api.rest.ApiErros;
import com.renatoschlogel.exception.RegistroNaoEncontrado;
import com.renatoschlogel.exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException exception) {
        return new ApiErros(exception.getMessage());
    }

    @ExceptionHandler(RegistroNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleRegistroNaoEncontrado(RegistroNaoEncontrado exception) {
        return new ApiErros(exception.getMessage());
    }

}
