package com.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Quote entity
 */


//TODO With lombock doesnt work
//@Data
public class Quote {

    private String quoteResponse;

    public String getQuoteResponse() {
        return quoteResponse;
    }

    public void setQuoteResponse(String quoteResponse) {
        this.quoteResponse = quoteResponse;
    }
}
