package ski.serwon.petassistant.mockinitializer;

import org.mockito.Mockito;
import ski.serwon.petassistant.entity.user.User;
import ski.serwon.petassistant.security.LoginDetailsService;

public class LoginDetailsServiceMockInitializer {
    public static void setUpLoginDetailsServiceMock(LoginDetailsService detailsServiceMock, User mockedUser) {
        setUpGetCurrentUser(detailsServiceMock, mockedUser);
    }

    private static void setUpGetCurrentUser(LoginDetailsService detailsServiceMock, User mockedUser) {
        Mockito.when(detailsServiceMock.getCurrentUser()).then(invocationOnMock -> mockedUser);
    }
}
