package se.omegapoint.mobvshermits.json.resrobot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = StopLocation.Builder.class)
@Value
@Builder(builderClassName = "Builder")
public class StopLocation {
    private final String name;
    private final int products;
    private final Float lon;
    private final Float lat;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
