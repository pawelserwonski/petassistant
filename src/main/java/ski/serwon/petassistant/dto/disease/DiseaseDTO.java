package ski.serwon.petassistant.dto.disease;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ski.serwon.petassistant.dto.animal.AnimalDTO;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDTO {
    Long id;
    private AnimalDTO sickAnimal;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String description;
}
