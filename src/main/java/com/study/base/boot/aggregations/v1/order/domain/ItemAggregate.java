package com.study.base.boot.aggregations.v1.order.domain;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.ItemStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.ItemRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Table(catalog = "base", name = "item")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class ItemAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private ItemStatusEnum status;
    private int price;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @LastModifiedDate
    private LocalDateTime deletedDate;

    public ItemAggregate create(ItemRepository itemRepository) {
        itemRepository.save(this);
        return this;
    }

    public ItemAggregate patch(CreateItem createItem) {
        this.itemName = StringUtils.defaultIfEmpty(createItem.getItemName(), this.itemName);
        this.price = createItem.getPrice();
        this.createdDate = LocalDateTime.now();
        createItem.getItemName();
        return this;
    }


}
