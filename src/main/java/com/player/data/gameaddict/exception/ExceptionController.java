package com.player.data.gameaddict.exception;

import com.amazonaws.AmazonServiceException;
import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.model.response.MetaDataRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({IOException.class, AmazonServiceException.class})
    public ResponseEntity<MetaDataRes<?>> handleExceptionFileUpload(Exception e) {
        log.error("File upload invalid with exception: {}", e.getMessage());
        return new ResponseEntity<>(new MetaDataRes<>(MetaDataEnum.FILE_ERROR), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<MetaDataRes<?>> handleException(Exception e) {
        log.error("Failed in progress with exception: {}", e.getMessage());
        return new ResponseEntity<>(new MetaDataRes<>(MetaDataEnum.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
