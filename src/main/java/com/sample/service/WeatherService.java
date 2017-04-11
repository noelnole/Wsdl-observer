package com.sample.service;

import com.sample.model.Quote;
import hello.wsdl.GetQuoteResponse;

import java.util.Optional;

/**
 * Service layer
 * @author Noel Rodriguez
 */
public interface WeatherService {


    public Optional<Quote> getQuote(String ticker);
}
