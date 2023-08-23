package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //AccessLevel 무분별한 객체 생성에 대해 한번 더 체크할 수 있는 수단 생성자 자동생성
@AllArgsConstructor(access = AccessLevel.PROTECTED)  // 모든 필드 값을 파라메터로 받는 생성자를  생성함.
public class CreateOrdersDto {
    @NotNull
    @Valid
    private List<CreateOrderDto> createOrders;
}
