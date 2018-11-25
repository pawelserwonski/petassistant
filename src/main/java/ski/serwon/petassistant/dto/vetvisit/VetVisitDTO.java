package ski.serwon.petassistant.dto.vetvisit;

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
public class VetVisitDTO {
    private Long id;
    private AnimalDTO animal;
    private LocalDateTime visitDate;
    private String reason;
    private String location;
    private String vetOpinion;
}
