package com.github.ramilyamar.subsreadfile.db;

import com.github.ramilyamar.subsreadfile.Main;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbProperties {
    private String url;
    private String user;
    private String password;
    private String driverClass;

    public static DbProperties read() {
        InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return new DbProperties(url, user, password, "org.h2.Driver");
    }
}
