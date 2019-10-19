package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.app.Application;
import com.github.ramilyamar.subsreadfile.db.Database;
import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.file.FileDao;
import com.github.ramilyamar.subsreadfile.file.FileDaoImpl;
import com.github.ramilyamar.subsreadfile.subs.SimpleWordsExtractor;
import com.github.ramilyamar.subsreadfile.subs.SubsLoader;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;
import com.github.ramilyamar.subsreadfile.word.MovieWordLinkDao;
import com.github.ramilyamar.subsreadfile.word.MovieWordLinkDaoImpl;
import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordDaoImpl;

import java.io.File;
import java.io.IOException;

public class Main {

    public static final String defaultDictionary = "src\\main\\resources\\eng-rus.dict";

    public static void main(String[] args) throws IOException {
        Database database = new Database();
        MovieWordLinkDao linkDao = new MovieWordLinkDaoImpl(database);
        FileDao fileDao = new FileDaoImpl(database);
        WordDao wordDao = new WordDaoImpl(database);
        Application app = new Application(
                new SubsLoader(
                        new SimpleWordsExtractor(),
                        new SimpleDictionaryParser()
                                .parse(new File(args.length > 0 ? args[0] : defaultDictionary)),
                        fileDao,
                        wordDao,
                        linkDao),
                new UserDaoImpl(database),
                fileDao,
                wordDao);
        app.run();
    }
}
