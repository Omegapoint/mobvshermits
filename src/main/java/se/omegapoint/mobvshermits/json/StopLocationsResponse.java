package se.omegapoint.mobvshermits.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@JsonDeserialize(builder = StopLocationsResponse.Builder.class)
@Value
@Builder(builderClassName = "Builder")
public class StopLocationsResponse {
    Collection<StopLocation> stopLocations;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        @JsonProperty("StopLocation")
        public void setStopLocation(Collection<se.omegapoint.mobvshermits.json.StopLocation> stopLocations) {
            this.stopLocations = stopLocations;
        }
    }
}
