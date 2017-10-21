package se.omegapoint.mobvshermits;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Config {

    private final Properties properties = new Properties();

    public Config() {
        final InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream("secret.properties");
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            log.error("Failed to load properties!");
        }
    }

    public String resrobotKey() throws IOException {
        return properties.getProperty("resrobot.key");
    }
}
