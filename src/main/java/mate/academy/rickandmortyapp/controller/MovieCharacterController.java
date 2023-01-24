package mate.academy.rickandmortyapp.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.rickandmortyapp.dto.MovieCharacterResponseDto;
import mate.academy.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import mate.academy.rickandmortyapp.service.MovieCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class MovieCharacterController {
    private final MovieCharacterService movieCharacterService;
    private final MovieCharacterMapper mapper;

    public MovieCharacterController(MovieCharacterService movieCharacterService,
                                    MovieCharacterMapper mapper) {
        this.movieCharacterService = movieCharacterService;
        this.mapper = mapper;
    }

    @GetMapping("/parse")
    @ApiOperation(value = "create a new product")
    public String parseFromApi() {
        movieCharacterService.sync();
        return "Done.";
    }

    @GetMapping("/random")
    public MovieCharacterResponseDto getRandomCharacter() {
        return mapper.toResponseDto(movieCharacterService.getRandomCharacter());
    }

    @GetMapping("/by-name")
    public List<MovieCharacterResponseDto> findAllByName(@RequestParam String name) {
        return movieCharacterService.findAllByNameContains(name)
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
