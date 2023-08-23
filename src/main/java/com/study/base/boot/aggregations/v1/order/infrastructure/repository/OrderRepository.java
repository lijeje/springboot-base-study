package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {
    //@Repository를 달면 dB랑 연결해주는 bean으로 인식
    //JpaRepository를 사용할 서비스가 있어야함. orderService
}
