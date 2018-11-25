package ski.serwon.petassistant.model.vetvisit;

import lombok.*;
import ski.serwon.petassistant.model.animal.Animal;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VetVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Animal animal;

    private LocalDateTime visitDate;
    private String reason;
    private String location;
    private String vetOpinion;
}
