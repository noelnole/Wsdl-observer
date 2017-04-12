package com.sample.service.impl;

import com.sample.configuration.OrikaConfig;
import com.sample.model.Quote;
import com.sample.repository.QuoteClient;
import hello.wsdl.GetQuoteResponse;
import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.sample.service.WeatherService;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Service layer
 * @author Noel Rodriguez
 */

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "quote")
public class WeatherServiceDefault implements WeatherService {

    @Autowired
    private QuoteClient quoteClient;



    @Cacheable
    @Override
    public Optional<Quote> getQuote(String ticker) {
        //I create an observable that emits a particular item
        MapperFacade mapper = OrikaConfig.getMapperFacade();
        Observable<GetQuoteResponse> getQuoteResponseObservable = Observable.just(quoteClient.marshalSendAndReceive(ticker));
        if (getQuoteResponseObservable == null){
            return Optional.empty();
        }else
            return Optional.of((mapper.map(getQuoteResponseObservable.blockingFirst(), Quote.class)));


    }


}
