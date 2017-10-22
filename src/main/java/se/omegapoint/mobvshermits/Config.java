package se.omegapoint.mobvshermits;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class Config {

    private final Properties properties = System.getProperties();

    public Config() {
        final Optional<InputStream> propertiesStream
                = Optional.ofNullable(this.getClass().getClassLoader().getResourceAsStream("secret.properties"));
        propertiesStream.ifPresent(this::loadPropsFromStream);
    }

    private void loadPropsFromStream(InputStream propertiesStream) {
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load properties!", e);
        }
    }

    public String resrobotKey() {
        return Optional.ofNullable(properties.getProperty("resrobot.key"))
            .orElseGet(this::getKeyFromEnv);
    }

    public int port() {
        final String port = Optional.ofNullable(System.getenv("PORT")).orElse("8080");
        return Integer.parseInt(port);
    }

    private String getKeyFromEnv() {
        return Optional.ofNullable(System.getenv("RESROBOT_KEY"))
                .orElseThrow(() -> new IllegalStateException("Resrobot API key config not found"));
    }
}
