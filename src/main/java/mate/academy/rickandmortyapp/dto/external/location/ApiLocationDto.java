package mate.academy.rickandmortyapp.dto.external.location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiLocationDto {
    private Long id;
    private String name;
    private String type;
    private String dimension;
    private String[] residents;
    private String url;
    private String created;
}
