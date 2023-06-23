package com.example.securityservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String name;

    private String password;

    private String role;

    private String email;
}
