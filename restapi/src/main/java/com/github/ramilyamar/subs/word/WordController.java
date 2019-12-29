package com.github.ramilyamar.subs.word;

import com.github.ramilyamar.subsreadfile.word.WordDao;
import com.github.ramilyamar.subsreadfile.word.WordInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/words")
@AllArgsConstructor
public class WordController {

    private WordDao wordDao;

    @GetMapping
    public List<WordInfo> getWords(@RequestParam long userId) {
        return wordDao.getWordsByUserId(userId);
    }
}
