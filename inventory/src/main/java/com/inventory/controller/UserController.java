package com.inventory.controller;


import com.inventory.dto.LoginDto;
import com.inventory.dto.LoginResponse;
import com.inventory.dto.RegisterDto;
import com.inventory.models.Owner;
import com.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Owner> getAll() {
        return userService.getAll();
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
        userService.save(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}
