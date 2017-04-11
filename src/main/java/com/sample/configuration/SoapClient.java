package com.sample.configuration;

import hello.wsdl.GetQuote;
import hello.wsdl.GetQuoteResponse;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.annotation.PostConstruct;

/**
 * This class extends from WebServiceGatewaySupport to return a GetQuoteReponse.
 * An alternative of this impletation could be using generic class:
 * SoapClient<R,T> with this clase we can avoid use GetQuoteResponse
 * and use R as the response and T how the request
 *
 * @author Noel Rodriguez
 */
@Slf4j
public abstract class SoapClient extends WebServiceGatewaySupport {

    @Autowired
    private Jaxb2Marshaller marshaller;

    public GetQuoteResponse marshalSendAndReceive(String ticker) {
        GetQuote request = new GetQuote();
        request.setSymbol(ticker);
        try {
            return (GetQuoteResponse) getWebServiceTemplate().marshalSendAndReceive("http://www.webservicex.com/stockquote.asmx",
                    request,
                    new SoapActionCallback("http://www.webserviceX.NET/GetQuote"));
        } catch (SoapFaultClientException e) {
            logger.error("Error", e);
            return null;
        }
    }

    @PostConstruct
    private void init() {
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

}