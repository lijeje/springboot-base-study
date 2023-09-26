package com.study.base.boot.aggregations.v1.order.presentation.mapper;

import com.study.base.boot.aggregations.v1.order.application.dto.res.OrderDto;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.config.mapstruct.base.BaseEntity;
import com.study.base.boot.config.mapstruct.mapper.SupportEntityToDtoMapper;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderEDMapper extends SupportEntityToDtoMapper<OrderAggregate, OrderDto> {
    /*
    @Mapper이걸 달아줘야 맵스트럭트 인식하고 컴포넌트로 만들어줌.
    nullValueCheckStrategy 널을 항상 체크할건지 말건지.
    널이 아닌경우만 냅핑을 해줌. componentModel??
     */

    @Condition
    default boolean isLazyLoaded(List<? extends BaseEntity> entities){
        return isLoaded(entities);
    }
}
