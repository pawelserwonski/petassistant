package ski.serwon.petassistant.entity.vaccine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ski.serwon.petassistant.entity.animal.Animal;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Animal vaccinatedAnimal;

    private LocalDateTime visitDate;
    private String sicknessType;
    private String location;


}
