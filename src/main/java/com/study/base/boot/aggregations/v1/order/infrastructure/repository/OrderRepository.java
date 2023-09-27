package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {
    //@Repository를 달면 dB랑 연결해주는 bean으로 인식
    //JpaRepository를 사용할 서비스가 있어야함. orderService

    Page<OrderAggregate> findAllByStatus(OrderStatusEnum status, Pageable pageable);

    Page<OrderAggregate> getPeriodAndPriceList(LocalDateTime searchStartDate
                                             , LocalDateTime searchEndDate
                                             , int minPrice
                                             , Pageable pageable);




}
