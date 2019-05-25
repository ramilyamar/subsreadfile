package com.github.ramilyamar.subsreadfile;

import com.github.ramilyamar.subsreadfile.commands.SubsLoader;
import com.github.ramilyamar.subsreadfile.enums.Command;
import com.github.ramilyamar.subsreadfile.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public Application(SubsLoader subsLoader) {
        this.subsLoader = subsLoader;
    }

    private SubsLoader subsLoader;

    void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        loop:
        while (true) {
            String fullCommand = reader.readLine();
            String commandText = StringUtil.substringBefore(fullCommand, " ");
            Command command = Command.valueOf(commandText.toUpperCase()); // TODO: 25.05.2019 handle unknown command

            switch (command) {
                case ADD:
                    subsLoader.load(StringUtil.substringAfter(fullCommand, " "));
                    break;
                case EXIT:
                    break loop;
                default:
                    System.out.println("Not implemented yet! Haha");
            }
        }
    }
}