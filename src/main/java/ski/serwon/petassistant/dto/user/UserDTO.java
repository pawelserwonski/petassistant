package ski.serwon.petassistant.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ski.serwon.petassistant.dto.animal.AnimalDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String photo;
    private List<AnimalDTO> animals;
    private String email;
    private String password;
}
