package mate.academy.rickandmortyapp.service;

import mate.academy.rickandmortyapp.model.Resident;

import java.util.List;
import java.util.Optional;

public interface ResidentService {
    List<Resident> findAllByIdIn(List<Long> ids);

    Optional<Resident> findByUrl(String url);

    Resident save(Resident resident);
}
