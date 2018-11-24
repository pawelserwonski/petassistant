package ski.serwon.petassistant.model.feed;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FodderUnit {
    MILLILITER("milliliter"),
    LITER("liter"),
    MILLIGRAM("milligram"),
    GRAM("gram");

    private String name;


}
