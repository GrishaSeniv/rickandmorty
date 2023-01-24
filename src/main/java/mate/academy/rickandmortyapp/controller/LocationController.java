package mate.academy.rickandmortyapp.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.rickandmortyapp.dto.LocationResponseDto;
import mate.academy.rickandmortyapp.dto.mapper.LocationMapper;
import mate.academy.rickandmortyapp.service.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locations")
public class LocationController {
    private final LocationService locationService;
    private final LocationMapper mapper;

    public LocationController(LocationService locationService, LocationMapper mapper) {
        this.locationService = locationService;
        this.mapper = mapper;
    }

    @GetMapping("/parse")
    @ApiOperation(value = "Parse characters from api to db")
    public String parseFromApi() {
        locationService.sync();
        return "Done";
    }

    @GetMapping
    @ApiOperation(value = "Get all locations")
    public List<LocationResponseDto> findAll() {
        return locationService.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
