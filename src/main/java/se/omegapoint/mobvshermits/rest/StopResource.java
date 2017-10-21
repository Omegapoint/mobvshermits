package se.omegapoint.mobvshermits.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import se.omegapoint.mobvshermits.Config;
import se.omegapoint.mobvshermits.json.StopLocation;
import se.omegapoint.mobvshermits.json.StopLocationsResponse;

import javax.inject.Inject;
import javax.ws.rs.QueryParam;
import java.net.URI;
import java.util.Collection;

@RestController
public class StopResource {
    public static final String HTTPS_API_RESROBOT_SE_V2 = "https://api.resrobot.se/v2";
    private final RestTemplate restTemplate;
    private final Config config;

    @Inject
    StopResource(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @RequestMapping("/stop")
    public Collection<StopLocation> getStopsByName(@QueryParam("query") String query) {
        final URI uri = UriComponentsBuilder.fromUriString(HTTPS_API_RESROBOT_SE_V2 + "/location.name")
                .queryParam("key", config.resrobotKey())
                .queryParam("format", "json")
                .queryParam("input", query)
                .build().toUri();
        final ResponseEntity<StopLocationsResponse> responseEntity = restTemplate.getForEntity(uri, StopLocationsResponse.class);
        return responseEntity.getBody().getStopLocations();
    }
}
