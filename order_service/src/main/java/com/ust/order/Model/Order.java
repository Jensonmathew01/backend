package com.ust.order.Model;

import com.ust.order.VO.product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    private Integer orderid;
    private Integer userid;

    private Integer amount;

    @ElementCollection
    private List<product> orderItems;

}
