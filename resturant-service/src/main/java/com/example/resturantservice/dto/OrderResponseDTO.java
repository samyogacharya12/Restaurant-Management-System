package com.example.resturantservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO implements Serializable {

    private String orderId;
    private String name;
    private int qty;
    private double price;
    private String orderDate;
    private String status;
    private int estimateDeliveryWindow;
}
