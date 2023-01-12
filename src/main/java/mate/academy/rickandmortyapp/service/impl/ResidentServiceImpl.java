package mate.academy.rickandmortyapp.service.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmortyapp.model.Resident;
import mate.academy.rickandmortyapp.repository.ResidentRepository;
import mate.academy.rickandmortyapp.service.ResidentService;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository repository;

    public ResidentServiceImpl(ResidentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Resident> findAllByIdIn(List<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    @Override
    public Optional<Resident> findByUrl(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public Resident save(Resident resident) {
        return repository.save(resident);
    }
}
