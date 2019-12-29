package com.github.ramilyamar.subs.config;

import com.github.ramilyamar.subsreadfile.db.Database;
import com.github.ramilyamar.subsreadfile.db.DatabaseImpl;
import com.github.ramilyamar.subsreadfile.user.LoginCommand;
import com.github.ramilyamar.subsreadfile.user.UserDao;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public Database database() {
        return new DatabaseImpl();
    }

    @Bean
    public WordDao wordDao(Database database) {
        return new WordDaoImpl(database);
    }

    @Bean
    public UserDao userDao(Database database) {
        return new UserDaoImpl(database);
    }

    @Bean
    public LoginCommand loginCommand(UserDao userDao) {
        return new LoginCommand(userDao);
    }
}
