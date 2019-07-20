package com.github.ramilyamar.subsreadfile.db;

import com.github.ramilyamar.subsreadfile.user.EncryptedPassword;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    UserDaoImpl dao = new UserDaoImpl(new Database());

    @Test
    void createUser() {
        String expectedName = "user1";
        long id = dao.createUser(expectedName, new EncryptedPassword("2", "2"));
        Option<String> name = dao.getUserNameById(id);
        assertEquals(Option.of(expectedName), name);
    }
}