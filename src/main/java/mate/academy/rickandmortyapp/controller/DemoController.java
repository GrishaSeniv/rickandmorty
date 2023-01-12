package mate.academy.rickandmortyapp.controller;

import mate.academy.rickandmortyapp.dto.ApiResponseDto;
import mate.academy.rickandmortyapp.model.Gender;
import mate.academy.rickandmortyapp.model.MovieCharacter;
import mate.academy.rickandmortyapp.model.Status;
import mate.academy.rickandmortyapp.service.HttpClient;
import mate.academy.rickandmortyapp.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/demo")
public class DemoController {
    private final MovieCharacterService movieCharacterService;
    private final HttpClient httpClient;

    public DemoController(MovieCharacterService movieCharacterService, HttpClient httpClient) {
        this.movieCharacterService = movieCharacterService;
        this.httpClient = httpClient;
    }

    @GetMapping
    public String runDemo() {
        movieCharacterService.syncExternalCharacters();
        return "Done.";
    }

    @GetMapping("/locations")
    public String findAll() {
        httpClient.get("https://rickandmortyapi.com/api/location", ApiResponseDto.class);
        return "Find all locations done";
    }
    @GetMapping("/update")
    public String updateDemo() {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setExternalId(826L);
        movieCharacter.setName("Test");
        movieCharacter.setSpecies("asd");
        movieCharacter.setType("");
        movieCharacter.setUrl("");
        movieCharacter.setCreated(LocalDateTime.now().toString());
        movieCharacter.setGender(Gender.MALE);
        movieCharacter.setStatus(Status.ALIVE);
        return "updated";
    }
}
