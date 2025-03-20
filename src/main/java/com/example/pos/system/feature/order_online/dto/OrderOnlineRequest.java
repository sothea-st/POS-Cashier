package com.example.pos.system.feature.order_online.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

public record OrderOnlineRequest(
        @NotBlank(message = "The field orderDate is required !")
        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "The orderDate must be in the format yyyy-MM-dd!"
        )
        String orderDate ,

//        @NotBlank(message = "The field orderNumber is required !")
//        String orderNumber,

//        @NotBlank(message = "The field orderStatus is required !")
//        String orderStatus,

        @NotBlank(message = "The field customerId is required !")
        String customerId,

        @NotBlank(message = "The field customerName is required !")
        String customerName,

        @NotBlank(message = "The field phoneNumber is required !")
        @Pattern(regexp = "\\d+", message = "The field phoneNumber must contain only digits!")
        String phoneNumber,

        @NotBlank(message = "The field deliveryInformation is required !")
        String deliveryInformation,

        @NotNull(message = "The field totalAmount is required !")
        BigDecimal totalAmount,

        @NotBlank(message = "The field paymentMethod is required !")
        String paymentMethod,

        @NotBlank(message = "The field paymentStatus is required !")
        String paymentStatus,

        @NotBlank(message = "The field deliveryAddress is required !")
        String deliveryAddress,

        @NotBlank(message = "The field customerNote is required !")
        String customerNote,

        @NotNull(message = "The field subTotal is required !")
        BigDecimal subTotal,

        @NotNull(message = "The field discount is required !")
        BigDecimal discount,

        @NotNull(message = "The field deliveryFee is required !")
        BigDecimal deliveryFee,

        @NotNull(message = "The field grandTotal is required !")
        BigDecimal grandTotal,

        @NotEmpty(message = "The field detail is required !")
        List<OrderOnlineDetailRequest> detail,

        @NotNull(message = "The field createdBy is required !")
        Integer createdBy

        ) {
}
