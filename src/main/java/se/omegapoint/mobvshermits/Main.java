package se.omegapoint.mobvshermits;

import spark.Spark;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class Main {
    public static void main(String... args) {
        final Config config = new Config();
        Spark.port(8080);
        Spark.get("/hello", (req, res) -> "Hello World");
        Spark.get("/stops", (req, res) -> getLocationsByName(config, "Skövde"));
    }

    private static String getLocationsByName(Config config, String name) {
        final Response response = ClientBuilder.newClient()
                .target("https://api.resrobot.se/v2/location.name?key=" + config.resrobotKey() + "&input=" +
                        name + "&format=json")
                .request().get();
        return response.readEntity(String.class);
    }
}
