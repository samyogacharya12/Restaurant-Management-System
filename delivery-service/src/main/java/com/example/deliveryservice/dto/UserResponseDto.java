package com.example.deliveryservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto implements Serializable {

    private String name;
    private String password;
    private String role;
}
