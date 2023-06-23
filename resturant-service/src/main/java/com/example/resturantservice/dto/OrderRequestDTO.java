package com.example.resturantservice.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private String name;
    private int quantity;
    private double price;
    private String orderDate;
    private String status;
    private String timeLine;
}
