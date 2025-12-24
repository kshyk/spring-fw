package com.kshyk.sfw.consumingrest.app;

import com.kshyk.sfw.consumingrest.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@Slf4j
@SpringBootApplication
public class ConsumingRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestClient restClient) {
        return args -> {
            var quote = restClient.get()
                    .uri("https://quoters.apps.pcfone.io/api/random")
                    .retrieve()
                    .body(Quote.class);
            log.info(quote.toString());
        };
    }
}
