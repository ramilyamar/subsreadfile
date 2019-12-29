package com.github.ramilyamar.subs.config;

import com.github.ramilyamar.subsreadfile.user.LoginCommand;
import com.github.ramilyamar.subsreadfile.user.LoginResult;
import com.github.ramilyamar.subsreadfile.user.Role;
import com.github.ramilyamar.subsreadfile.user.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private LoginCommand loginCommand;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        Role[] roles = new Role[1];
        LoginResult result = loginCommand.execute(roles, new UserInfo[1], new String[]{"", name, password});
        if (result == LoginResult.SUCCESS) {
            List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                    .map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(
                    name, password, authorities);
        } else if (result == LoginResult.USER_NOT_FOUND) {
            throw new UsernameNotFoundException("User not found");
        } else {
            throw new BadCredentialsException("Wrong password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
