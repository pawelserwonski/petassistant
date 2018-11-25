package ski.serwon.petassistant.dto.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ski.serwon.petassistant.dto.animal.AnimalDTO;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedDTO {
    private Long id;
    private AnimalDTO fedAnimal;
    private LocalTime time;
    private String fodderType;
    private double portionSize;
    private String portionSizeUnit;
}
