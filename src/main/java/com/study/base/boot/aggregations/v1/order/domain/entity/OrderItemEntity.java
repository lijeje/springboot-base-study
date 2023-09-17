package com.study.base.boot.aggregations.v1.order.domain.entity;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrderItem;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderItemStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(catalog = "base", name ="order_item")
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Getter
@EntityListeners(AuditingEntityListener.class)
public class OrderItemEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private String itemName;

    @Enumerated(EnumType.STRING)
    private OrderItemStatusEnum status;
    private int price;
    private int qty;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)//FetchType는 조회할때 많이 쓰는데 조회가  나중에되는거 주문만 조회했어 주문을 가져왔는데  fetch에 레이지를 안쓰면  아이템까지 한번에 조회 안스면 아이템은 조회안됨.
    @JoinColumn(name = "orderId")//외래키가 있으면 조인컬럼을 쓰고
    private OrderAggregate order;

    public OrderItemEntity  putOrder(OrderAggregate order){
        this.order = order;

        return this;
    }

    /*
    *
    */
    public OrderItemEntity patch(CreateOrderItem createOrderItem){
        this.itemId  = createOrderItem.getItemId();
        this.itemName = StringUtils.defaultIfEmpty(createOrderItem.getItemName(), this.itemName);
        this.price  = createOrderItem.getPrice();
        this.qty = createOrderItem.getQty();

        return this;
    }
}