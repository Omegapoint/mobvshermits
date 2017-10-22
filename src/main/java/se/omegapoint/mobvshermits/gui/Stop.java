package se.omegapoint.mobvshermits.gui;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = Stop.Builder.class)
@Value
@Builder(builderClassName = "Builder")
public class Stop {
    String name;
    Collection<String> products;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
    }
}
