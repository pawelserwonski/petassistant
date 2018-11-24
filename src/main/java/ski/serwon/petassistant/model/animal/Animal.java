package ski.serwon.petassistant.model.animal;

import lombok.*;
import ski.serwon.petassistant.model.disease.Disease;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.model.user.User;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import ski.serwon.petassistant.model.walk.Walk;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User owner;

    private String species;
    private String breed;
    private LocalDate birthDate;
    private String name;
    private String photo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vaccine> vaccines;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Walk> walks;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feed> feedings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disease> diseasesHistory;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VetVisit> vetVisitsHistory;
}
