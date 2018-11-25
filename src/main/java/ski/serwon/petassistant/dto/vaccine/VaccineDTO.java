package ski.serwon.petassistant.dto.vaccine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ski.serwon.petassistant.dto.animal.AnimalDTO;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDTO {
    private Long id;
    private AnimalDTO vaccinatedAnimal;
    private LocalDateTime visitDate;
    private String sicknessType;
    private String location;
}
