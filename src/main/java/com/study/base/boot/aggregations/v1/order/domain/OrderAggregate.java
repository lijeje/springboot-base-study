package com.study.base.boot.aggregations.v1.order.domain;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import com.study.base.boot.config.mapstruct.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.domain.Page;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(catalog = "base", name = "order")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@SuperBuilder
@Getter
@EntityListeners(AuditingEntityListener.class)
@Transactional(readOnly = true)
public class OrderAggregate extends BaseEntity {
    //jpa개념에서 Entity는 테이블이랑 맵핑되는 객체를 의미 어떤 테이블인지는 @Table에 정의
    //@DynamicInsert는 성능이슈가 있고 이미 자기 자신을 넣어주기때문에 사용하지 않음 .
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String orderName;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> items;

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

        createOrder.getItems()
                .forEach(item -> this.addItem(
                        OrderItemEntity.builder()
                                .build()
                                .patch(item)
                ));

        return this;
    }

    public OrderAggregate addItem(OrderItemEntity orderItem) {
        Assert.notNull(orderItem, "orderItem is null");

        if(this.getItems() == null) {
            this.items =  new ArrayList<>();
        }

        orderItem.putOrder(this);
        this.items.add(orderItem);

        return this;
    }

    public Page<OrderAggregate> getPeriodAndPriceList(LocalDateTime searchStartDate
                                                    , LocalDateTime searchEndDate
                                                    , int minPrice
                                                    , Pageable pageable) {
//        Page<OrderAggregate> getPeriodAndPriceList = orderRepository.getPeriodAndPriceList(searchStartDate
//                                                                                         , searchEndDate
//                                                                                         , minPrice
//                                                                                         , pageable);
        return getPeriodAndPriceList;
    }
}
