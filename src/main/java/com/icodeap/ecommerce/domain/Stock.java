package com.icodeap.ecommerce.domain;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Stock {
        private Integer id;
        private Integer unitIn;
        private Integer unitOut;
        private String description;
        private Integer balance;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;
        private Product product;
}
