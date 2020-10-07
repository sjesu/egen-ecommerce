package com.egen.oms.orders.repository;

import com.egen.oms.orders.model.db.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdeRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNum(String orderNum);

}
