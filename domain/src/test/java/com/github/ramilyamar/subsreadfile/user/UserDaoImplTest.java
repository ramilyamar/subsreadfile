package com.github.ramilyamar.subsreadfile.user;

import com.github.ramilyamar.subsreadfile.db.TestDatabase;
import com.github.ramilyamar.subsreadfile.db.TestUtil;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDaoImplTest {

    private UserDaoImpl dao = new UserDaoImpl(TestDatabase.getInstance().getDatabase());

    @Test
    void createUser() {
        String expectedName = TestUtil.uniqueString();
        long id = dao.createUser(expectedName, new EncryptedPassword("2", "2"));
        Option<String> name = dao.getUserNameById(id);
        assertEquals(Option.of(expectedName), name);
    }

    @Test
    void createdUserShouldHaveRoleUser() {
        String username = TestUtil.uniqueString();
        dao.createUser(username, new EncryptedPassword("2", "2"));
        Option<UserInfo> userInfo = dao.getUserInfoByName(username);
        assertTrue(userInfo.isDefined());
        userInfo.onDefined(u -> assertEquals(Role.USER, u.getRole()));
    }

    @Test
    void shouldReturnNoneIfUserDoesNotExist() {
        Option<UserInfo> userInfoByName = dao.getUserInfoByName(TestUtil.uniqueString());
        assertEquals(Option.none(), userInfoByName);
    }
}
