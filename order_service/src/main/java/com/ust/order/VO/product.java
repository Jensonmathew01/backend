package com.ust.order.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class product {


    private Integer id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;

}
