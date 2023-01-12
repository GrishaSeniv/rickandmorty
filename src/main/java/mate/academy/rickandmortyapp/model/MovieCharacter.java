package mate.academy.rickandmortyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_characters")
public class MovieCharacter {
    @Id
    @GeneratedValue(generator = "movie_character_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "movie_character_id_seq",
            sequenceName = "movie_character_id_seq",
            allocationSize = 1)
    private Long id;
    @JoinColumn(name = "external_id")
    private Long externalId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String species;
    private String type;
    private String url;
    private String created;
    @Enumerated(EnumType.STRING)
    private Status status;
//    private Object origin;
//    private Object location;
//    private Object episode;
}
