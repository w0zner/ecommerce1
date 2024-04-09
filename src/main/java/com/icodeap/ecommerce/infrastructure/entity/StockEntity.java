package com.icodeap.ecommerce.infrastructure.entity;

import com.icodeap.ecommerce.domain.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name="stock")
@NoArgsConstructor
@Data
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer unitIn;
    private Integer unitOut;
    private String description;
    private Integer balance;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductEntity productEntity;
}
