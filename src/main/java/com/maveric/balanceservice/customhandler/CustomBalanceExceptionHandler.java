package com.maveric.balanceservice.customhandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maveric.balanceservice.dto.ErrorResponseDto;
import com.maveric.balanceservice.exception.AccounIdMismatchException;
import com.maveric.balanceservice.exception.AmountisNegative;
import com.maveric.balanceservice.exception.NoBalanceFoundException;
import com.maveric.balanceservice.exception.NoBalancesException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

import java.util.List;


@ControllerAdvice
public class CustomBalanceExceptionHandler {
    Logger logger = LoggerFactory.getLogger(CustomBalanceExceptionHandler.class);


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> invalidMethodArgumentsException(MethodArgumentNotValidException ex) {
        logger.trace("Inside CustomBalanceExceptionHandler invalidMethodArgumentsException()  method");

        List<String> errorList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().
                forEach(error -> errorList.add(error.getDefaultMessage()));
        ErrorResponseDto responseDto = new ErrorResponseDto("400", errorList.get(errorList.size() - 1));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> invalidValueException(InvalidFormatException ex) {
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
    public ResponseEntity<ErrorResponseDto> noBalancesExceptionHandler(NoBalancesException ex){
        logger.trace("Inside CustomBalanceExceptionHandler NoBalancesExceptionHandler()  method");

        //HttpMessageNotReadableException
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setCode("404");
        responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> noBalanceFoundExceptionHandler(NoBalanceFoundException ex){
        logger.trace("Inside CustomBalanceExceptionHandler 2 NoBalanceFoundExceptionHandler()  method");

        //HttpMessageNotReadableException
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setCode("404");
        responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> amountisNegativeException(AmountisNegative ex){
        logger.trace("Inside CustomBalanceExceptionHandler amountisNegativeException()  method");

        //HttpMessageNotReadableException
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setCode("400");
        responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDto> illegalArgumentExceptionHandler(IllegalArgumentException ex){
        logger.trace("Inside CustomBalanceExceptionHandler illegalArgumentExceptionHandler() method");

        ErrorResponseDto responseDto = new ErrorResponseDto("400", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
