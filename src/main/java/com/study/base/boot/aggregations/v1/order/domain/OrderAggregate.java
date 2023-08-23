package com.study.base.boot.aggregations.v1.order.domain;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Table(catalog = "base", name = "order")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Getter
public class OrderAggregate {
    //jpa개념에서 Entity는 테이블이랑 맵핑되는 객체를 의미 어떤 테이블인지는 @Table에 정의
    //@DynamicInsert는 성능이슈가 있고 이미 자기 자신을 넣어주기때문에 사용하지 않음 .
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String orderName;
    private String status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public OrderAggregate create(OrderRepository orderRepository) {
        orderRepository.save(this);

        return this;
    }

    public OrderAggregate patch(CreateOrder createOrder) {
        this.orderNumber = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.orderNumber);
        this.orderName = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.orderName);
        this.price = createOrder.getPrice();
        this.deliveryFee = createOrder.getDeliveryFee();
        this.address = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.address);
        this.userId = createOrder.getUserId();
        this.createdDate = LocalDateTime.now();

        return this;
    }
}
