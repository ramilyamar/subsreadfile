package com.github.ramilyamar.subsreadfile.file;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class FileInfo {
    private final String name;
    private final long userId;
    private final String movieName;
}
