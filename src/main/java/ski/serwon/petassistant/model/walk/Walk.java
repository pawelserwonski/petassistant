package ski.serwon.petassistant.model.walk;

import lombok.*;
import ski.serwon.petassistant.model.animal.Animal;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Walk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Animal walkedOutAnimal;

    private LocalDate startDate;    //when walks start
    private LocalDate endDate;
    private LocalTime startTime;
    private List<DayOfWeek> daysOfWeek;
    private Double walkLength;
    private WalkUnit walkLengthUnit;
}
