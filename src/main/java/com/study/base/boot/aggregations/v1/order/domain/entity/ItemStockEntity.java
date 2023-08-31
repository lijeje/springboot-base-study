package com.study.base.boot.aggregations.v1.order.domain.entity;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateItemStock;
import com.study.base.boot.aggregations.v1.order.domain.ItemAggregate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
    @Table(catalog = "base", name ="ItemStock")
    @Entity
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    @DynamicInsert
    @Getter
    @EntityListeners(AuditingEntityListener.class)
    public class ItemStockEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int stock_qty;

        @CreatedDate
        private LocalDateTime createdDate;

        @LastModifiedDate
        private LocalDateTime updatedDate;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="itemId")
        private ItemAggregate item;


        public ItemStockEntity putItem(ItemAggregate item){
            this.item = item;
            return this;
        }

        public ItemStockEntity patch(CreateItemStock createItemStock) {
            this.stock_qty = createItemStock.getStockQty();
            return this;
        }
}
