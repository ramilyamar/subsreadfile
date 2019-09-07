package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.app.Application;
import com.github.ramilyamar.subsreadfile.db.Database;
import com.github.ramilyamar.subsreadfile.dict.SimpleDictionaryParser;
import com.github.ramilyamar.subsreadfile.file.FileDaoImpl;
import com.github.ramilyamar.subsreadfile.subs.SimpleWordsExtractor;
import com.github.ramilyamar.subsreadfile.subs.SubsLoader;
import com.github.ramilyamar.subsreadfile.user.UserDaoImpl;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String defaultDictionary = "src\\main\\resources\\eng-rus.dict";
        Database database = new Database();
        Application app = new Application(
                new SubsLoader(
                        new SimpleWordsExtractor(),
                        new SimpleDictionaryParser().parse(new File(args.length > 0 ? args[0] : defaultDictionary)),
                        new FileDaoImpl(database)),
                new UserDaoImpl(database));
        app.run();
    }
}
