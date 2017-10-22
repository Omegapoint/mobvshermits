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
    String name;
    int products;
    Float lon;
    Float lat;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
