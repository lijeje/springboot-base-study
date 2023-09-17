package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.CreateItemService;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateItem;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateItemDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateItemsDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrderDto;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestApi("/v1/createItem")
@Slf4j
@RequiredArgsConstructor
public class CreateItemController {
    private final CreateItemService createItemService;

    @Post
    public List<Long> createItems(@RequestBody @Valid CreateItemsDto request) {
        final List<CreateItem> createItemList = request.getCreateItems().stream().map(CreateItemDto::toCreate).toList();
        return createItemService.createItems(createItemList);
    }
}
