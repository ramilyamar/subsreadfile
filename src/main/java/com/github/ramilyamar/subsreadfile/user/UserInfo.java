package com.github.ramilyamar.subsreadfile.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserInfo {

    private final long id;
    private final Role role;
    private final EncryptedPassword encryptedPassword;
}
