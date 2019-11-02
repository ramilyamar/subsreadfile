package com.github.ramilyamar.subsreadfile.user;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class PasswordUtils {

    private PasswordUtils() {
    }

    private static final Random RANDOM = new SecureRandom();

    /**
     * All characters of the alphabet and numbers
     * for further use in creating a random number for password encryption.
     */
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * Count of iterations during encryption.
     */
    private static final int ITERATIONS = 10000;

    /**
     * Length of key during encryption.
     */
    private static final int KEY_LENGTH = 256;

    /**
     * Compares entered password and password saved in database.
     *
     * @param providedPassword  entered password
     * @param encryptedPassword encrypted password
     * @return {@code true} if entered password is equal to password in database
     */
    public static boolean verifyUserPassword(String providedPassword, EncryptedPassword encryptedPassword) {
        String newSecurePassword = encryptPassword(providedPassword, encryptedPassword.getSalt());
        return newSecurePassword.equals(encryptedPassword.getPassword());
    }

    /**
     * Returns encrypted password with two parts - password and salt.
     *
     * @param password entered password
     * @return encrypted password with two parts - password and salt
     */
    public static EncryptedPassword encryptPassword(String password) {
        String salt = generateSalt(30);
        String encryptedPassword = encryptPassword(password, salt);
        return new EncryptedPassword(encryptedPassword, salt);
    }

    private static String encryptPassword(String password, String salt) {
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        return Base64.getEncoder().encodeToString(securePassword);
    }

    private static String generateSalt(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return stringBuilder.toString();
    }

    private static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Ошибка при хешировании пароля " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
}
