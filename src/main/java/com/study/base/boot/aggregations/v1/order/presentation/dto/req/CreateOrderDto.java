package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateOrderDto {
    @NotNull
    private String orderNumber;

    @NotNull
    private String orderName;
    private int price;
    private int deliveryFee;

    @NotNull
    private String address;
    private long userId;

//    @NotNull
//    @Valid
//    private List<CreateOrderItemDto> items;

    public CreateOrder toCreate(){
        return CreateOrder.builder()
                .orderNumber(this.orderNumber)
                .orderName(this.orderName)
                .price(this.price)
                .deliveryFee(this.deliveryFee)
                .address(this.address)
                .userId(this.userId)
                .build();
    }

}
