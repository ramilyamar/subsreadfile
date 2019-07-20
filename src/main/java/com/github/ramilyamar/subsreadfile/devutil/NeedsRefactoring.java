package com.github.ramilyamar.subsreadfile.devutil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NeedsRefactoring(value = "Move into separate library", urgency = Urgency.LOW)
@Retention(RetentionPolicy.SOURCE)
public @interface NeedsRefactoring {
    String value() default "";
    Urgency urgency() default Urgency.MEDIUM;
}
