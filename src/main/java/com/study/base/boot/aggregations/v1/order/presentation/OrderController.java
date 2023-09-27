package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.OrderService;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.application.dto.res.OrderDto;
import com.study.base.boot.aggregations.v1.order.application.dto.res.OrderItemDto;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import com.study.base.boot.aggregations.v1.order.presentation.mapper.OrderEDMapper;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestApi("/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderEDMapper orderEDMapper;
    @Get("/status/{status}")
    public Page<OrderDto> getOrders(@PathVariable OrderStatusEnum status
            , @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderAggregate> pageOrders = orderService.listByStatus(status,pageable);
        List<OrderAggregate> orders = pageOrders.getContent();
        List<OrderDto> orderDtos = orders.stream().map(order -> orderEDMapper.toDto(order)).collect(Collectors.toList());
        return new PageImpl<>(orderDtos, pageable, pageOrders.getTotalElements());
    }

    @Post
    public List<Long> createOrders(@RequestBody @Valid CreateOrdersDto request) {
        final List<CreateOrder> createOrderList = request.getCreateOrders().stream().map(CreateOrderDto::toCreate).toList();
        return orderService.createOrders(createOrderList);
    }

    /* [주문내역을 가져오는 컨트롤러]
    * endPoint url의 끝부분
    * PathVariable을 쓰면 endPoint를 받아 올 수 있음
    *
    */
    @Get("/{id}")
    public OrderDto getOrder(@PathVariable long id){
        OrderAggregate orderAggregate = orderService.get(id);

      OrderDto orderDto = orderEDMapper.toDto(orderAggregate);
      return orderDto;

    }




    @Get("/conditions")
    public Page<OrderDto> getConditionsList(@RequestParam(value = "searchStartDate", required = false) LocalDateTime searchStartDate
                                          , @RequestParam(value = "searchEndDate", required = false) LocalDateTime searchEndDate
                                          , @RequestParam int minPrice
                                          , @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<OrderAggregate> pageOrders = orderService.getPeriodAndPriceList(
                searchStartDate,
                searchEndDate,
                minPrice,
                pageable
        );
        List<OrderAggregate> orders = pageOrders.getContent();

        List<OrderDto> orderDtos = orders.stream().map(orderEDMapper::toDto).toList();

        return new PageImpl<>(orderDtos, pageable, pageOrders.getTotalElements());
    }



}