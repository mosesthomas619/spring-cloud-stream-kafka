
import java.util.function.Function;


import com.example.processor.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.GenericMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProcessorFunctionTest {

    private ConfigurableApplicationContext context;


    ObjectMapper objectMapper = new ObjectMapper();

    @AfterAll
    public void cleanUp() {
        this.context.close();
    }


    @Test
    public void testSingleFunction() throws JsonProcessingException {

        User userUnprocessed = new User();
        userUnprocessed.setId(1);
        userUnprocessed.setProcessed(false);
        userUnprocessed.setSaved(false);


        User expectedProcessedUser = new User();
        expectedProcessedUser.setId(1);
        expectedProcessedUser.setProcessed(true);
        expectedProcessedUser.setSaved(false);

        this.context = new SpringApplicationBuilder(TestChannelBinderConfiguration
                .getCompleteConfiguration(FunctionsConfiguration.class))
                .web(WebApplicationType.NONE)
                .run("--spring.cloud.stream.function.definition=process");

        InputDestination source = this.context.getBean(InputDestination.class);
        OutputDestination target = this.context.getBean(OutputDestination.class);
        source.send(new GenericMessage<>(userUnprocessed));
        User actualProcessedUser = objectMapper.readValue(new String(target.receive().getPayload()), User.class);
        assertEquals(expectedProcessedUser.toString(), actualProcessedUser.toString());
    }



    @EnableAutoConfiguration
    public static class FunctionsConfiguration {

        @Bean
        public Function<User, User> process () {
            return input ->
            {
                input.setProcessed(true);
                return input;
            };
        }
    }

}
