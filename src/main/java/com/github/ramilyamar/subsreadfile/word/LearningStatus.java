package com.github.ramilyamar.subsreadfile.word;

import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LearningStatus {
    NEW_WORD(1),
    LEARNING(2),
    LEARNED(3);

    private final int id;

    static Option<LearningStatus> fromInt(int id) {
        for (LearningStatus learningStatus : LearningStatus.values()) {
            if (learningStatus.id == id) {
                return Option.some(learningStatus);
            }
        }
        return Option.none();
    }
}
