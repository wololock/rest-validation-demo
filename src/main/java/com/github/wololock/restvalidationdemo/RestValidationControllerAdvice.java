package com.github.wololock.restvalidationdemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestValidationControllerAdvice {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Map> handleValidationFailure(ConstraintViolationException ex) {

        final Map<String, Object> response = new HashMap<>();
        response.put("message", "Your request contains errors");
        response.put("errors", ex.getConstraintViolations()
                .stream()
                .map(it -> new HashMap<String, String>() {{
                    put(it.getPropertyPath().toString(), it.getMessage());
                }})
                .collect(Collectors.toList())
        );

        return ResponseEntity.badRequest().body(response);
    }
}
