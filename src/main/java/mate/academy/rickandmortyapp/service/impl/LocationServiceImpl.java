package mate.academy.rickandmortyapp.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.rickandmortyapp.dto.location.ApiLocationDto;
import mate.academy.rickandmortyapp.dto.location.ApiLocationResponseDto;
import mate.academy.rickandmortyapp.dto.mapper.LocationMapper;
import mate.academy.rickandmortyapp.model.Location;
import mate.academy.rickandmortyapp.repository.LocationRepository;
import mate.academy.rickandmortyapp.repository.ResidentRepository;
import mate.academy.rickandmortyapp.service.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;
    private HttpClient httpClient;
    private LocationMapper mapper;
    private ResidentRepository residentRepository;

    public LocationServiceImpl(LocationRepository locationRepository, HttpClient httpClient, LocationMapper mapper, ResidentRepository residentRepository) {
        this.locationRepository = locationRepository;
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.residentRepository = residentRepository;
    }

    @Override
    public void sync() {
        ApiLocationResponseDto apiLocationResponseDto = httpClient.get(
                "https://rickandmortyapi.com/api/location", ApiLocationResponseDto.class);
        saveDtosToDB(apiLocationResponseDto);
        while (apiLocationResponseDto.getInfo().getNext() != null) {
            apiLocationResponseDto = httpClient.get(
                    apiLocationResponseDto.getInfo().getNext(), ApiLocationResponseDto.class);
            saveDtosToDB(apiLocationResponseDto);
        }
    }

    private void saveDtosToDB(ApiLocationResponseDto responseDto) {
        Map<Long, ApiLocationDto> externalCharacters = Arrays.stream(responseDto.getResults())
                .collect(Collectors.toMap(ApiLocationDto::getId, Function.identity()));
        Set<Long> externalIds = externalCharacters.keySet();
        Map<Long, Location> existingCharacters = locationRepository.findAllByExternalIdIn(externalCharacters.keySet())
                .stream()
                .collect(Collectors.toMap(Location::getExternalId, Function.identity()));
        Set<Long> existingIds = existingCharacters.keySet();
        updateIfExist(externalIds, existingIds);
        externalIds.removeAll(existingIds);
        List<Location> charactersToSave = externalIds.stream()
                .map(id -> mapper.parseApiLocationDto(externalCharacters.get(id)))
                .collect(Collectors.toList());
        locationRepository.saveAll(charactersToSave);
    }

    private void updateIfExist(Set<Long> externalIds, Set<Long> existingIds) {
        for (Long externalId : externalIds) {
            if (existingIds.contains(externalId)) {
                locationRepository.save(locationRepository.getById(externalId));
            }
        }
    }
}
