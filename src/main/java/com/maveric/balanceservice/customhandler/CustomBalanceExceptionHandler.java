package com.maveric.balanceservice.customhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maveric.balanceservice.dto.ErrorResponseDto;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.NoBalancesException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomBalanceExceptionHandler {
    Logger logger = LoggerFactory.getLogger(CustomBalanceExceptionHandler.class);


    @ExceptionHandler
    public ResponseEntity invalidMethodArgumentsException(MethodArgumentNotValidException ex) {
        logger.trace("Inside CustomBalanceExceptionHandler invalidMethodArgumentsException()  method");

        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().
                forEach(error -> errorList.add(error.getDefaultMessage()));
        ErrorResponseDto responseDto = new ErrorResponseDto("400", errorList.get(errorList.size() - 1));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity invalidValueException(InvalidFormatException ex) {
        logger.trace("Inside CustomBalanceExceptionHandler invalidValueException()  method");

        //HttpMessageNotReadableException
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setCode("400");
        if (ex.getMessage().contains("enum")) {
            responseDto.setMessage("Cannot have any other values for Currency other than enum [INR, DOLLAR, EURO]");
        } else
            responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler //for enum
    public ResponseEntity<ErrorResponseDto> contraintViolation(ConstraintViolationException ex) {
        logger.trace("Inside CustomBalanceExceptionHandler contraintViolation()  method");

        ErrorResponseDto responseDto = new ErrorResponseDto("400", StringUtils.substringBetween(ex.getMessage(), "'", "'"));
        //getConstraintViolations()
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> accountIdMismatchExceptionHandler(AccounIdMismatchException ex){
        logger.trace("Inside CustomBalanceExceptionHandler accountIdMismatchExceptionHandler()  method");

        //HttpMessageNotReadableException
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setCode("400");
        responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> NoBalancesExceptionHandler(NoBalancesException ex){
        logger.trace("Inside CustomBalanceExceptionHandler accountIdMismatchExceptionHandler()  method");

        //HttpMessageNotReadableException
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setCode("404");
        responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
}
