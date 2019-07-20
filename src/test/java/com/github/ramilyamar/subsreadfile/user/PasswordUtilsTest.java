package com.github.ramilyamar.subsreadfile.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    @Test
    void verifyCorrectPasswordShouldReturnTrue() {
        String password = "123";
        EncryptedPassword encryptedPassword = PasswordUtils.encryptPassword(password);

        boolean actualResult = PasswordUtils.verifyUserPassword(password, encryptedPassword);
        assertEquals(true, actualResult);
    }

    @Test
    void verifyWrongPasswordShouldReturnFalse() {
        String password = "123";
        String wrongPassword = "456";
        EncryptedPassword encryptedPassword = PasswordUtils.encryptPassword(password);

        boolean actualResult = PasswordUtils.verifyUserPassword(wrongPassword, encryptedPassword);
        assertEquals(false, actualResult);
    }
}
