package com.study.base.boot.aggregations.v1.order.application.dto.res;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SuperBuilder
@Getter
public class OrderDto extends BaseDto {
    private Long id;
    private String orderNumber;
    private String orderName;

    private OrderStatusEnum status;
    private int deliveryFee;
    private int price;
    private String address;
    private Long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<OrderItemDto>  items;
}
