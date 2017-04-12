package com.bns.baas.location.web;

import com.sample.model.Quote;
import com.sample.service.WeatherService;
import com.sample.web.WeatherController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Ver simple test
 * @author Noel Rodriguez
 */
public class DefaultWeatherController {

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private WeatherService weatherService;


    private MockMvc mockMvc;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private static String PATH = "/api";

    private static String TICKER = "MSFT";


    @Before
    public void setUpMockMvc() {
        mockMvc = standaloneSetup(weatherController).build();
    }


    /**
     * Dummy test
     * @throws Exception
     */
    @Test
    public void weather() throws Exception {

        Quote quote = new Quote();
        when(weatherService.getQuote(TICKER))
                .thenReturn(Optional.of(quote));

        mockMvc.perform(get(PATH+"/weathers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
