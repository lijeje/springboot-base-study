package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.ItemAggregate;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.ItemRepository;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateItemService {
    //@RequiredArgsConstructor -생성자를 만들어주는  역할 final은 무조건 초기값세팅이 되어야하는데 final이 달린애들을 생성자를 만들어
    // 줌.
    //@Autowired bean을 setter로도 주입해줌.


    private final ItemRepository itemRepository;

    @Transactional
    public void create(CreateItem createItem) {
        final var itemAggregate = ItemAggregate.builder()
                .build()
                .patch(createItem)
                .create(itemRepository);

    }

    public List<Long> createItems(List<CreateItem> createItemList) {
        final List<ItemAggregate> items = createItemList.stream()
                .map(createItem  -> ItemAggregate.builder()
                        .build()
                        .patch(createItem)
                ).toList();
        List<ItemAggregate> createItems = itemRepository.saveAll(items);
        return itemRepository.saveAll(items).stream().map(ItemAggregate::getId).collect(Collectors.toList());
    }
}
