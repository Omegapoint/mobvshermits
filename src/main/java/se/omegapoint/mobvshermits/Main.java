package se.omegapoint.mobvshermits;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    private static String getLocationsByName(Config config, String name) {
        final Response response = ClientBuilder.newClient()
                .target("https://api.resrobot.se/v2/location.name?key=" + config.resrobotKey() + "&input=" +
                        name + "&format=json")
                .request().get();
        return response.readEntity(String.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean Config config() {
        return new Config();
    }
}
