package mate.academy.rickandmortyapp.dto.mapper;

import mate.academy.rickandmortyapp.dto.MovieCharacterResponseDto;
import mate.academy.rickandmortyapp.dto.external.character.ApiCharacterDto;
import mate.academy.rickandmortyapp.model.Gender;
import mate.academy.rickandmortyapp.model.MovieCharacter;
import mate.academy.rickandmortyapp.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseCharacterApiResponseDto(ApiCharacterDto apiCharacterDto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setExternalId(apiCharacterDto.getId());
        movieCharacter.setName(apiCharacterDto.getName());
        movieCharacter.setGender(Gender.valueOf(apiCharacterDto.getGender().toUpperCase()));
        movieCharacter.setSpecies(apiCharacterDto.getSpecies());
        movieCharacter.setType(apiCharacterDto.getType());
        movieCharacter.setUrl(apiCharacterDto.getUrl());
        movieCharacter.setCreated(apiCharacterDto.getCreated());
        movieCharacter.setStatus(Status.valueOf(apiCharacterDto.getStatus().toUpperCase()));
        return movieCharacter;
    }

    public MovieCharacterResponseDto toResponseDto(MovieCharacter character) {
        MovieCharacterResponseDto movieCharacterResponseDto = new MovieCharacterResponseDto();
        movieCharacterResponseDto.setId(character.getId());
        movieCharacterResponseDto.setName(character.getName());
        movieCharacterResponseDto.setGender(character.getGender());
        movieCharacterResponseDto.setSpecies(character.getSpecies());
        movieCharacterResponseDto.setType(character.getType());
        movieCharacterResponseDto.setUrl(character.getUrl());
        movieCharacterResponseDto.setCreated(character.getCreated());
        movieCharacterResponseDto.setStatus(character.getStatus());
        return movieCharacterResponseDto;
    }
}
