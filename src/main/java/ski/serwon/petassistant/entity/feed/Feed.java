package ski.serwon.petassistant.entity.feed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ski.serwon.petassistant.entity.animal.Animal;

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
