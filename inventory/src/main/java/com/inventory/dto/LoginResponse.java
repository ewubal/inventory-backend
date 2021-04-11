package com.inventory.dto;

import com.inventory.security.DbRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private int id;
    private String email;
    private String token;
    private DbRole role;
}
