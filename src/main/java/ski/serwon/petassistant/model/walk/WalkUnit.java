package ski.serwon.petassistant.model.walk;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WalkUnit {
    METER("meter"),
    KILOMETER("kilometer"),
    MINUTE("minute"),
    HOUR("hour");

    private String name;
}
