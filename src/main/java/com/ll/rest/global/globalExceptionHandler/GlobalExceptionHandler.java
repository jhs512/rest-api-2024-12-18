package com.ll.rest.global.globalExceptionHandler;

import com.ll.rest.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RsData<Void>> handle(NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new RsData<>(
                        "404-1",
                        "해당 데이터가 존재하지 않습니다."
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RsData<Void>> handle(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String field = fieldError.getField();
        String code = fieldError.getCode();
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RsData<>(
                        "400-1",
                        field + " : " + message
                ));
    }
}