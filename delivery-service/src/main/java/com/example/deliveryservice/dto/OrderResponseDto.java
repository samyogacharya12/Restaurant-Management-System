package com.example.deliveryservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto implements Serializable {

    private String orderId;
    private String name;
    private int qty;
    private double price;
    private String orderDate;
    private String status;
    private int estimateDeliveryWindow;
}
