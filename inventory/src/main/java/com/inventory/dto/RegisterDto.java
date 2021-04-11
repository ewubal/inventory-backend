package com.inventory.dto;

import lombok.*;



@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String name;
    private String surname;
    private String password;
    private String email;
}
