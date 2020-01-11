package com.github.ramilyamar.subs.config;

import com.github.ramilyamar.subsreadfile.db.Database;
import com.github.ramilyamar.subsreadfile.db.DatabaseImpl;
import com.github.ramilyamar.subsreadfile.db.DbProperties;
import com.github.ramilyamar.subsreadfile.db.MigrationUtil;
import com.github.ramilyamar.subsreadfile.user.LoginCommand;
import com.github.ramilyamar.subsreadfile.user.UserDao;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordDaoImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "db")
    DbProperties dbProperties() {
        return new DbProperties();
    }

    @Bean
    public Database database(DbProperties dbProperties) {
        DatabaseImpl database = new DatabaseImpl(dbProperties);
        MigrationUtil.createTables(database);
        return database;
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
