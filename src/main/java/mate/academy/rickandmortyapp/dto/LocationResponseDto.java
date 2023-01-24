package mate.academy.rickandmortyapp.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mate.academy.rickandmortyapp.model.Resident;

@Getter
@Setter
@ToString
public class LocationResponseDto {
    private Long id;
    private String name;
    private String type;
    private String dimension;
    private List<Resident> resident;
    private String url;
    private String created;
}
