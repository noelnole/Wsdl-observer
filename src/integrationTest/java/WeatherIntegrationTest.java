
import com.sample.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * Account integration test
 * It is a simple integration test, the important part of this test is how works
 * integration test with SpringBootTest
 *
 * @author Noel Rodriguez
 */


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
@ActiveProfiles("test")
public class WeatherIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static String PATH = "api";

    private MockMvc mockMvc;


    @Before
    public void setUpMockMvc() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAccountById() throws Exception {

        mockMvc.perform(get(PATH +"/weathers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}


