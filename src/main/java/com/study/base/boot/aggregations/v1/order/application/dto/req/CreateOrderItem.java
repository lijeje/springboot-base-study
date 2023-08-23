package com.study.base.boot.aggregations.v1.order.application.dto.req;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderItem {

    private Long itemId;

    private String itemName;

    private int price;

    private int qty;

}
