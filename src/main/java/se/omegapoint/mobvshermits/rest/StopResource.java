package se.omegapoint.mobvshermits.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import se.omegapoint.mobvshermits.Config;
import se.omegapoint.mobvshermits.gui.Product;
import se.omegapoint.mobvshermits.gui.Stop;
import se.omegapoint.mobvshermits.json.resrobot.StopLocation;
import se.omegapoint.mobvshermits.json.resrobot.StopLocationsResponse;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Collections2.transform;
import static java.util.stream.Collectors.toList;

@RequestMapping("/")
@RestController
public class StopResource {
    public static final String HTTPS_API_RESROBOT_SE_V2 = "https://api.resrobot.se/v2";
    private final RestTemplate restTemplate;
    private final Config config;

    @Autowired
    StopResource(RestTemplate restTemplate, Config config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @GetMapping("/stop")
    public Collection<StopLocation> getStopsByName(@NotNull @QueryParam("query") String query) {
        final URI uri = UriComponentsBuilder.fromUriString(HTTPS_API_RESROBOT_SE_V2 + "/location.name")
                .queryParam("key", config.resrobotKey())
                .queryParam("format", "json")
                .queryParam("input", query)
                .build().toUri();
        return restTemplate.getForEntity(uri, StopLocationsResponse.class).getBody().getStopLocations();
    }

    @GetMapping("/near")
    public Collection<Stop> getStopsNearBy(@NotNull @QueryParam("lat") Float lat,
                                           @NotNull @QueryParam("lon") Float lon) {
        final URI uri = UriComponentsBuilder.fromUriString(HTTPS_API_RESROBOT_SE_V2 + "/location.nearbystops")
                .queryParam("key", config.resrobotKey())
                .queryParam("format", "json")
                .queryParam("originCoordLat", lat)
                .queryParam("originCoordLong", lon)
                .build().toUri();
        final Collection<StopLocation> stopLocations = restTemplate.getForEntity(uri, StopLocationsResponse.class).getBody().getStopLocations();
        return transform(stopLocations, StopResource::stop);
    }

    private static Stop stop(StopLocation stopLocation) {
        final List<Product> products = Arrays.stream(Product.values())
                .filter(product -> (product.getMask() & stopLocation.getProducts()) != 0)
                .collect(toList());
        return Stop.builder().products(products).name(stopLocation.getName()).build();
    }
}
