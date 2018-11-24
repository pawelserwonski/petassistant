package ski.serwon.petassistant.model.walk;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private LocalDate startDate;    //when walks start
    private LocalDate endDate;
    private LocalTime startTime;
    private List<DayOfWeek> daysOfWeek;
    private Double walkLength;
    private WalkUnit walkLengthUnit;
}
