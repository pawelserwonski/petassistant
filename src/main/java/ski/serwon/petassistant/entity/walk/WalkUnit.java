package ski.serwon.petassistant.entity.walk;

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
