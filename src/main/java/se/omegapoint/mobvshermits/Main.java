package se.omegapoint.mobvshermits;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;


@EnableAutoConfiguration
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
}
