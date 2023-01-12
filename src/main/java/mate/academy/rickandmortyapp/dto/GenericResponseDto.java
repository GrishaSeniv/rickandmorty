package mate.academy.rickandmortyapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class GenericResponseDto<T> {
    private ApiInfoDto info;
    private T[] results;
}
