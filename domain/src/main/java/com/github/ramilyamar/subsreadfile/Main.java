package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.app.Application;
import com.github.ramilyamar.subsreadfile.app.CommandLineViewer;
import com.github.ramilyamar.subsreadfile.db.Database;
import com.github.ramilyamar.subsreadfile.db.DatabaseImpl;
import com.github.ramilyamar.subsreadfile.db.DbProperties;
import com.github.ramilyamar.subsreadfile.db.MigrationUtil;
import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileDaoImpl;
import com.github.ramilyamar.subsreadfile.file.MoviesCommand;
import com.github.ramilyamar.subsreadfile.subs.AddCommand;
import com.github.ramilyamar.subsreadfile.subs.SimpleWordsExtractor;
import com.github.ramilyamar.subsreadfile.user.*;
import com.github.ramilyamar.subsreadfile.word.*;

import java.io.File;
import java.io.IOException;

public class Main {

    public static final String defaultDictionary = "domain\\src\\main\\resources\\eng-rus.dict";

    public static void main(String[] args) throws IOException {
        Database database = new DatabaseImpl(DbProperties.read());
        MigrationUtil.createTables(database);
        MovieWordLinkDao linkDao = new MovieWordLinkDaoImpl(database);
        FileDao fileDao = new FileDaoImpl(database);
        WordDao wordDao = new WordDaoImpl(database);
        UserDao userDao = new UserDaoImpl(database);

        Application app = new Application(
                new RegCommand(userDao),
                new LoginCommand(userDao),
                new LogoutCommand(),
                new AddCommand(
                        new SimpleWordsExtractor(),
                        new SimpleDictionaryParser()
                                .parse(new File(args.length > 0 ? args[0] : defaultDictionary)),
                        fileDao,
                        wordDao,
                        linkDao),
                new WordsCommand(wordDao),
                new MoviesCommand(fileDao),
                new CommandLineViewer());
        app.run();
    }
}
