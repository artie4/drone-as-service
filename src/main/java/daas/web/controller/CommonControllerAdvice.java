package daas.web.controller;

import daas.exception.InvalidDroneStateException;
import daas.exception.LowBatteryLevelException;
import daas.exception.NotFoundException;
import daas.exception.TooHeavyLoadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CommonControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TooHeavyLoadException.class)
    public ResponseEntity<Object> handleTooHeavyLoadException(TooHeavyLoadException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDroneStateException.class)
    public ResponseEntity<Object> handleInvalidDroneStateException(InvalidDroneStateException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LowBatteryLevelException.class)
    public ResponseEntity<Object> handleLowBatteryLevelException(LowBatteryLevelException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
