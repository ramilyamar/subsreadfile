package com.github.ramilyamar.subsreadfile.db;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    UserDaoImpl dao = new UserDaoImpl(new Database());

    @Test
    void createUser() {
        long id = dao.createUser("user1");
        System.out.println(id);
    }
}