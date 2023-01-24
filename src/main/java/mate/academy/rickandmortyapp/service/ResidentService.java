package mate.academy.rickandmortyapp.service;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmortyapp.model.Resident;

public interface ResidentService {
    List<Resident> findAllByIdIn(List<Long> ids);

    Optional<Resident> findByUrl(String url);

    Resident save(Resident resident);
}
