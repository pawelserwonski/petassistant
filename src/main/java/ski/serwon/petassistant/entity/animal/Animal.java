package ski.serwon.petassistant.entity.animal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ski.serwon.petassistant.entity.disease.Disease;
import ski.serwon.petassistant.entity.feed.Feed;
import ski.serwon.petassistant.entity.user.User;
import ski.serwon.petassistant.entity.vaccine.Vaccine;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;
import ski.serwon.petassistant.entity.walk.Walk;

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
    @JsonManagedReference
    private User owner;

    private String species;
    private String breed;
    private LocalDate birthDate;
    private String name;
    @Lob
    private String photo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vaccinatedAnimal")
    private List<Vaccine> vaccines;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "walkedOutAnimal")
    private List<Walk> walks;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "fedAnimal")
    private List<Feed> feeds;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sickAnimal")
    private List<Disease> diseasesHistory;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "animal")
    private List<VetVisit> vetVisitsHistory;
}
