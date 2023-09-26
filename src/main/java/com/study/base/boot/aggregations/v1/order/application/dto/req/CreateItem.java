package com.study.base.boot.aggregations.v1.order.application.dto.req;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class CreateItem {

    private String itemName;

    private int price;

}
