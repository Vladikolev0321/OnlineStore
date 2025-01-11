package com.example.onlineStore.dto;

import java.io.Serializable;

public record OrderDto(
        Long id,
        Double totalAmount,
        Long userId,
        Long paymentId
) {
}
