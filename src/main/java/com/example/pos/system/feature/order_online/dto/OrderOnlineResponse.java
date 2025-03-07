package com.example.pos.system.feature.order_online.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;
@Builder
public record OrderOnlineResponse(
    Integer id,
    String orderDate,
    String orderNumber,
    String orderStatus,
    String customerId,
    String customerName,
    String phoneNumber,
    String deliveryInformation,
    BigDecimal totalAmount,
    String paymentMethod,
    String paymentStatus,
    String deliveryAddress,
    String customerNote,
    BigDecimal subTotal,
    BigDecimal discount,
    BigDecimal deliveryFee,
    BigDecimal grandTotal,
    List<OrderProductDetailResponse> details,
    CalculateCountAndSum countAndSum
) {
}
