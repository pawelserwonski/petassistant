package ski.serwon.petassistant.model.disease;

import lombok.*;
import ski.serwon.petassistant.model.animal.Animal;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Animal sickAnimal;

    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String description;
}
