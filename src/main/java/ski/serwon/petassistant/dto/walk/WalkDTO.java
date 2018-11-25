package ski.serwon.petassistant.dto.walk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ski.serwon.petassistant.dto.animal.AnimalDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkDTO {
    private Long id;
    private AnimalDTO walkedOutAnimal;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private List<String> daysOfWeek;
    private double walkLength;
    private String walkLengthUnit;
}
