package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrderItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateOrderItemDto {
    @PositiveOrZero
    private long itemId;

    @NotNull
    private String itemName;

    @PositiveOrZero
    private int price;

    @PositiveOrZero
    private int qty;

    public CreateOrderItem toCreate() {
        return CreateOrderItem.builder()
                .itemId(this.itemId)
                .itemName(this.itemName)
                .price(this.price)
                .qty(this.qty)
                .build();
    }


}
