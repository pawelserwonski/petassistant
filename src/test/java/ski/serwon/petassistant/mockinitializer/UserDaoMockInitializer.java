package ski.serwon.petassistant.mockinitializer;

import org.mockito.Mockito;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.entity.user.User;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class UserDaoMockInitializer {
    public static void setUpUserDaoMock(UserDao userDaoMock, List<User> userMockList) {
        setUpUserSaveScenario(userDaoMock, userMockList);
        setUpUserFindScenario(userDaoMock, userMockList);
    }

    private static void setUpUserSaveScenario(UserDao userDaoMock, List<User> userMockList) {
        Mockito.when(userDaoMock.save(any(User.class))).then(invocationOnMock1 -> {
            User added = invocationOnMock1.getArgument(0);
            if (added.getId() == null) {
                added.setId(Long.valueOf(userMockList.size()));
                userMockList.add(added);
            } else {
                userMockList.set(added.getId().intValue(), added);
            }
            return added;
        });
    }

    private static void setUpUserFindScenario(UserDao userDaoMock, List<User> userMockList) {
        Mockito.when(userDaoMock.findById(anyLong())).thenAnswer(invocationOnMock ->
                userMockList.get(invocationOnMock.getArgument(0)));
    }
}
