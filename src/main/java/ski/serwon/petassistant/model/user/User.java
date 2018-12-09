package ski.serwon.petassistant.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ski.serwon.petassistant.model.animal.Animal;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(
        name = "email_user_uc", columnNames = "email"
))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Lob
    private String photo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    @JsonBackReference
    private List<Animal> animals;
}
