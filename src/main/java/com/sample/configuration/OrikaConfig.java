package com.sample.configuration;

import com.sample.model.Quote;
import hello.wsdl.GetQuoteResponse;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Orika COnfiguration
 * @author Noel Rodriguez
 */
public final class OrikaConfig {

    private static final MapperFactory mapperFactory;

    static {
        mapperFactory = new DefaultMapperFactory.Builder().build();

        ConverterFactory converterFactory = mapperFactory.getConverterFactory();

        mapperFactory.classMap(GetQuoteResponse.class, Quote.class)
                .field("getQuoteResult", "quoteResponse")
                .byDefault()
                .register();
    }

    private OrikaConfig() {
    }

    public static MapperFacade getMapperFacade() {
        return mapperFactory.getMapperFacade();
    }
}