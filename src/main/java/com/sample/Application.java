package com.sample;

import com.sample.repository.QuoteClient;
import hello.wsdl.GetQuoteResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Simple Spring Boot aplication to read from wsdl with rxjava and orika to mapper.
 *
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}



	@Bean
	CommandLineRunner lookup(QuoteClient quoteClient) {
		return args -> {
			String ticker = "MSFT";

			if (args.length > 0) {
				ticker = args[0];
			}
			//The response when Spring is starting.
			GetQuoteResponse response = quoteClient.marshalSendAndReceive(ticker);
			System.err.println(response.getGetQuoteResult());

		};
	}

}
