package com.example.shop.controller;

import com.example.shop.domain.dto.ErrorFieldDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@ResponseBody
public class AdviceController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorFieldDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
        return methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(objectError -> new ErrorFieldDetails(objectError.getCode(), objectError.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
