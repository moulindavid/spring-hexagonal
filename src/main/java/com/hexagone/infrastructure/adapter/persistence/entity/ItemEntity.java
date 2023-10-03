package com.hexagone.infrastructure.adapter.persistence.entity;

import com.hexagone.application.order.Item;
import com.hexagone.application.shared.Pastry;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Setter
@Getter
public class ItemEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated
    @NotNull
    private Pastry pastry;

    @NotNull
    private BigDecimal cost;


    @NotNull
    private Integer quantity;

    public Item toDomain() {
        return new Item(
                pastry,
                cost,
                quantity
        );
    }

    public static ItemEntity fromDomain(Item item) {
        var entity = new ItemEntity();
        entity.setPastry(item.pastry());
        entity.setQuantity(item.quantity());
        entity.setCost(item.cost());
        return entity;
    }
}
