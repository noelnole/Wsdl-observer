package com.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author  Noel Rodriguez
 */
//TODO currently I dont use this class, I use optional in service layer and web layer return 404
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WeatherException extends RuntimeException {
    public WeatherException() {
        super();
    }
    public WeatherException(String message) {
        super(message);
    }
    public WeatherException(Throwable cause) {
        super(cause);
    }
}