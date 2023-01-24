package mate.academy.rickandmortyapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mate.academy.rickandmortyapp.model.Gender;
import mate.academy.rickandmortyapp.model.Status;

@Getter
@Setter
@ToString
public class MovieCharacterResponseDto {
    private Long id;
    private String name;
    private Status status;
    private Gender gender;
    private String species;
    private String type;
    private String url;
    private String created;
}
