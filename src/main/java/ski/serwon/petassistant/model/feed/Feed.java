package ski.serwon.petassistant.model.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ski.serwon.petassistant.model.animal.Animal;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Animal fedAnimal;

    private LocalTime time;
    private String fodderType;
    private Double portionSize;
    private FodderUnit portionSizeUnit;

}
