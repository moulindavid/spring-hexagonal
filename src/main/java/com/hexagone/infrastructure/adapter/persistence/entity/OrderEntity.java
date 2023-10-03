package com.hexagone.infrastructure.adapter.persistence.entity;

import com.hexagone.application.order.Order;
import com.hexagone.application.shared.Location;
import com.hexagone.application.shared.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class OrderEntity {
    @Id
    private UUID id;

    @Enumerated
    @NotNull
    private Location location;

    @Enumerated
    @NotNull
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<ItemEntity> items;

    public Order toDomain() {
        return new Order(
                id,
                location,
                items.stream().map(ItemEntity::toDomain).toList(),
                status
        );
    }

    public static OrderEntity fromDomain(Order order) {
        var entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setLocation(order.getLocation());
        entity.setStatus(order.getStatus());
        entity.setItems(order.getItems().stream().map(ItemEntity::fromDomain).toList());
        return entity;
    }
}