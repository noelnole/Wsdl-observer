package com.sample.web;

import com.sample.aop.LogExecution;
import com.sample.exception.WeatherException;
import com.sample.model.Quote;
import com.sun.istack.internal.Interned;
import hello.wsdl.GetQuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sample.service.WeatherService;

import ma.glasnost.orika.MapperFacade;

import java.util.Optional;

/**
 * This is the api controller
 *
 * @author Noel Rodriguez
 */

@RestController
@RequestMapping(value = "api")
public class WeatherController {

    private static WeatherService weatherService;

    private static MapperFacade mapperFacade;


    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
        this.mapperFacade   = mapperFacade;
    }


    @GetMapping("weathers")
    @LogExecution
    public ResponseEntity<Quote> getWeather(){
        Optional<Quote> quote = weatherService.getQuote("MSFT");
        if (quote.isPresent())
            return new ResponseEntity<Quote>(quote.get(),HttpStatus.OK);
        else
            return new ResponseEntity<Quote>(HttpStatus.NOT_FOUND);
    }
}
