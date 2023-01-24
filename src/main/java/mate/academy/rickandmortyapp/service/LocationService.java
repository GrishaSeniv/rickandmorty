package mate.academy.rickandmortyapp.service;

import java.util.List;
import mate.academy.rickandmortyapp.model.Location;

public interface LocationService extends Syncable {
    List<Location> findAll();
}
