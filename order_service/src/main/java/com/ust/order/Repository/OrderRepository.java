package com.ust.order.Repository;

import com.ust.order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
    Optional<List<Order>> findByUserid(Integer userid);

    boolean existsByUserid(Integer userid);

}
