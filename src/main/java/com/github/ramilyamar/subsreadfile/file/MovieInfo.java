package com.github.ramilyamar.subsreadfile.file;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class MovieInfo {
    private long fileId;
    private long userId;
    private String movieName;
}
