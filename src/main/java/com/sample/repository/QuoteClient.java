
package com.sample.repository;

import com.sample.configuration.SoapClient;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class QuoteClient extends SoapClient {

    @PostConstruct
    private void setEndpoint() {
        setDefaultUri("http://www.webservicex.com/stockquote.asmx");
    }

}
