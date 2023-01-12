package mate.academy.rickandmortyapp.dto.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.rickandmortyapp.dto.location.ApiLocationDto;
import mate.academy.rickandmortyapp.model.Location;
import mate.academy.rickandmortyapp.model.Resident;
import mate.academy.rickandmortyapp.service.ResidentService;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    private final ResidentService residentService;
    private final ResidentMapper residentMapper;

    public LocationMapper(ResidentService residentService, ResidentMapper residentMapper) {
        this.residentService = residentService;
        this.residentMapper = residentMapper;
    }

    public Location parseApiLocationDto(ApiLocationDto apiLocationDto) {
        Location location = new Location();
        location.setExternalId(apiLocationDto.getId());
        location.setName(apiLocationDto.getName());
        location.setType(apiLocationDto.getType());
        location.setDimension(apiLocationDto.getDimension());
        location.setUrl(apiLocationDto.getUrl());
        location.setCreated(apiLocationDto.getCreated());
        location.setResident(residentService.findAllByIdIn(getResidentsIdsByUrls(apiLocationDto)));
        return location;
    }

    private List<Long> getResidentsIdsByUrls(ApiLocationDto apiLocationDto) {
        List<String> residentsUrls = Arrays.stream(apiLocationDto.getResidents())
                .collect(Collectors.toList());
        List<Long> residentsDBIds = new ArrayList<>();
        for (String url : residentsUrls) {
            if (residentService.findByUrl(url).isPresent()) {
                residentsDBIds.add(residentService.findByUrl(url).get().getId());
            } else {
                Resident savedResident = residentService.save(residentMapper.parseResidentArray(url));
                residentsDBIds.add(savedResident.getId());
            }
        }
        return residentsDBIds;
    }
}
