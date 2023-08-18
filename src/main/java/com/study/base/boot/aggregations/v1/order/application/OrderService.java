package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    //@RequiredArgsConstructor -생성자를 만들어주는  역할 final은 무조건 초기값세팅이 되어야하는데 final이 달린애들을 생성자를 만들어
    // 줌.
    //@Autowired bean을 setter로도 주입해줌.
    private final OrderRepository orderRepository;//여기서 save를 지원해줌.

    public void create(CreateOrder createOrder) {
        final var orderAggregate = OrderAggregate.builder()
                .build()
                .patch(createOrder)
                .create(orderRepository);

    }

    @Transactional
    public List<Long> createOrders(List<CreateOrder> orderList){
        final List<OrderAggregate>  orders = orderList.stream()
                                            .map(order  -> OrderAggregate.builder()
                                                        .build()
                                                        .patch(order)
                                            ).toList();

        List<OrderAggregate> saveOrders = orderRepository.saveAll(orders);
        return orderRepository.saveAll(orders).stream().map(OrderAggregate::getId).collect(Collectors.toList());
    }

}
