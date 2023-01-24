package mate.academy.rickandmortyapp.service;

import java.util.List;
import mate.academy.rickandmortyapp.model.MovieCharacter;

public interface MovieCharacterService extends Syncable {
    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContains(String name);
}
