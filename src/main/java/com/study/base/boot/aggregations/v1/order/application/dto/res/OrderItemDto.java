package com.study.base.boot.aggregations.v1.order.application.dto.res;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderItemStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseDto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

//@Builder
@SuperBuilder //부모까지 빌더패턴으로 만들어줌
@Getter
public class OrderItemDto extends BaseDto {
    private Long id;
    private Long itemId;
    private String itemName;
    private OrderItemStatusEnum status;
    private int price;
    private int qty;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
