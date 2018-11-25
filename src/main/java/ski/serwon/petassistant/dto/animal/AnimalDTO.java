package ski.serwon.petassistant.dto.animal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ski.serwon.petassistant.dto.disease.DiseaseDTO;
import ski.serwon.petassistant.dto.feed.FeedDTO;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.dto.vaccine.VaccineDTO;
import ski.serwon.petassistant.dto.vetvisit.VetVisitDTO;
import ski.serwon.petassistant.dto.walk.WalkDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {
    private Long id;
    private UserDTO owner;
    private String species;
    private String breed;
    private LocalDate birthDate;
    private String name;
    private String photo;
    private List<VaccineDTO> vaccines;
    private List<WalkDTO> walks;
    private List<FeedDTO> feeds;
    private List<DiseaseDTO> diseasesHistory;
    private List<VetVisitDTO> vetVisitsHistory;
}
