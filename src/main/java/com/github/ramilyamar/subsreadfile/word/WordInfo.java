package com.github.ramilyamar.subsreadfile.word;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WordInfo {
    private Long id;
    private final String word;
    private final Collection<String> translations;
    private final long fileId;
    private final long userId;
    private final LearningStatus learningStatus;

    public WordInfo(String word, Collection<String> translations, long fileId, long userId,
                    LearningStatus learningStatus) {
        this.word = word;
        this.translations = translations;
        this.fileId = fileId;
        this.userId = userId;
        this.learningStatus = learningStatus;
    }
}
