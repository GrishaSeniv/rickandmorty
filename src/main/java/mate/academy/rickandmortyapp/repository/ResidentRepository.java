package mate.academy.rickandmortyapp.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmortyapp.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    List<Resident> findAllByIdIn (List<Long> ids);

    Optional<Resident> findByUrl(String url);
}
