package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class CreateItemDto {
    @NotNull
    private String itemName;

    @PositiveOrZero
    private int price;

    @NotNull
    @Size(min = 1)
    @Valid
    private List<CreateOrderItemDto> items;

    public CreateItem toCreate() {
        return CreateItem.builder()
                .itemName(this.itemName)
                .price(this.price)
                .build();
    }
}
