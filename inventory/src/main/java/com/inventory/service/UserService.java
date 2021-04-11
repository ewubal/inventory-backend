package com.inventory.service;

import com.inventory.dto.LoginDto;
import com.inventory.dto.LoginResponse;
import com.inventory.dto.RegisterDto;
import com.inventory.exception.UserException;
import com.inventory.models.Owner;
import com.inventory.repository.UserRepository;
import com.inventory.security.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public List<Owner> getAll() {
        return userRepository.findAll();
    }

    public void save(RegisterDto registerDto) {
        Owner owner = Owner.builder()
                .name(registerDto.getName())
                .surname(registerDto.getSurname())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(DbRole.USER)
                .build();
        userRepository.save(owner);
    }

    public LoginResponse login(LoginDto loginDto) {
        if (isBlank(loginDto.getEmail())) {
            throw new UserException("Missing email");
        }
        if (isBlank(loginDto.getPassword())) {
            throw new UserException("Missing password");
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        MyUser myUser = (MyUser) authenticate.getPrincipal();
        String token = jwtTokenProvider.generateToken(myUser);
        return LoginResponse.builder()
                .id(myUser.getId())
                .email(myUser.getUsername())
                .token(token)
                .role(myUser.getDbRole())
                .build();
    }

}
