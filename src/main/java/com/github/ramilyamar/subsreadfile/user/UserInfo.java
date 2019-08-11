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
    private Role role;
    private EncryptedPassword encryptedPassword;
}
