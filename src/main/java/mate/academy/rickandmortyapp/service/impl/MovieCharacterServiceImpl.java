package mate.academy.rickandmortyapp.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import mate.academy.rickandmortyapp.dto.character.ApiCharacterDto;
import mate.academy.rickandmortyapp.dto.character.ApiCharacterResponseDto;
import mate.academy.rickandmortyapp.dto.mapper.MovieCharacterMapper;
import mate.academy.rickandmortyapp.model.MovieCharacter;
import mate.academy.rickandmortyapp.repository.MovieCharacterRepository;
import mate.academy.rickandmortyapp.service.MovieCharacterService;
import org.springframework.stereotype.Service;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {
    private final MovieCharacterRepository repository;
    private final HttpClient httpClient;
    private final MovieCharacterMapper mapper;

    public MovieCharacterServiceImpl(MovieCharacterRepository repository, HttpClient httpClient, MovieCharacterMapper mapper) {
        this.repository = repository;
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    @Override
    public void sync() {
        ApiCharacterResponseDto responseDto = httpClient.get("https://rickandmortyapi.com/api/character",
                ApiCharacterResponseDto.class);
        saveDtosToDB(responseDto);
        while (responseDto.getInfo().getNext() != null) {
            responseDto = httpClient.get(responseDto.getInfo().getNext(), ApiCharacterResponseDto.class);
            saveDtosToDB(responseDto);
        }
    }

    private void saveDtosToDB(ApiCharacterResponseDto responseDto) {
        Map<Long, ApiCharacterDto> externalCharacters = Arrays.stream(responseDto.getResults())
                .collect(Collectors.toMap(ApiCharacterDto::getId, Function.identity()));
        Set<Long> externalIds = externalCharacters.keySet();
        Map<Long, MovieCharacter> existingCharacters = repository.findAllByExternalIdIn(externalCharacters.keySet())
                .stream()
                .collect(Collectors.toMap(MovieCharacter::getExternalId, Function.identity()));
        Set<Long> existingIds = existingCharacters.keySet();
        updateIfExist(externalIds, existingIds);
        externalIds.removeAll(existingIds);
        List<MovieCharacter> charactersToSave = externalIds.stream()
                .map(id -> mapper.parseCharacterApiResponseDto(externalCharacters.get(id)))
                .collect(Collectors.toList());
        repository.saveAll(charactersToSave);
    }

    private void updateIfExist(Set<Long> externalIds, Set<Long> existingIds) {
        for (Long externalId : externalIds) {
            if (existingIds.contains(externalId)) {
                repository.save(repository.getById(externalId));
            }
        }
    }
}
