package com.github.ramilyamar.subsreadfile.devutil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation should be used for code that needs refactoring.
 */
@NeedsRefactoring(value = "Move into separate library", urgency = NeedsRefactoring.Urgency.LOW)
@Retention(RetentionPolicy.SOURCE)
public @interface NeedsRefactoring {

    /**
     * Message that explains what needs to be changed in the code.
     */
    String value() default "";

    /**
     * Grade of urgency of refactoring necessity.
     */
    Urgency urgency() default Urgency.MEDIUM;

    enum Urgency {
        HIGH, MEDIUM, LOW
    }
}
