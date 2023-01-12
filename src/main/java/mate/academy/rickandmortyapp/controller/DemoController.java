package mate.academy.rickandmortyapp.controller;

import mate.academy.rickandmortyapp.service.impl.HttpClient;
import mate.academy.rickandmortyapp.service.LocationService;
import mate.academy.rickandmortyapp.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final MovieCharacterService movieCharacterService;
    private final HttpClient httpClient;
    private final LocationService locationService;

    public DemoController(MovieCharacterService movieCharacterService, HttpClient httpClient, LocationService locationService) {
        this.movieCharacterService = movieCharacterService;
        this.httpClient = httpClient;
        this.locationService = locationService;
    }

    @GetMapping("/characters")
    public String runDemo() {
        movieCharacterService.syncExternalCharacters();
        return "Done.";
    }

    @GetMapping("/locations")
    public String findAll() {
        locationService.syncExternalLocations();
        return "Find all locations done";
    }
}
