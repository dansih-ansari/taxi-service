package com.hobby.taxisvc.controller.advise;

import com.hobby.taxisvc.contract.response.TaxiGenericResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.hobby.taxisvc.error.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalAdvise {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<TaxiGenericResponse> validation(MethodArgumentNotValidException exception) {
        var msg = exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .filter(x -> !x.isBlank()).collect(Collectors.joining(", "));
        var err = new TaxiError(msg, ErrorCodes.INVALID_DATA.name());
        var errs = List.of(err);
        var res = new TaxiGenericResponse(errs);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
    
}

