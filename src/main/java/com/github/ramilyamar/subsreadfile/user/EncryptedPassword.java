package com.github.ramilyamar.subsreadfile.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class EncryptedPassword {
    private final String password;
    private final String salt;
}
