package mate.academy.rickandmortyapp.repository;

import java.util.List;
import java.util.Set;
import mate.academy.rickandmortyapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByExternalIdIn(Set<Long> externalIds);
}
