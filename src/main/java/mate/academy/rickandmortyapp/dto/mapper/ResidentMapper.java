package mate.academy.rickandmortyapp.dto.mapper;

import mate.academy.rickandmortyapp.model.Resident;
import org.springframework.stereotype.Component;

@Component
public class ResidentMapper {
    public Resident parseResidentArray(String resident) {
        Resident residentToModel = new Resident();
        residentToModel.setUrl(resident);
        return residentToModel;
    }
}
