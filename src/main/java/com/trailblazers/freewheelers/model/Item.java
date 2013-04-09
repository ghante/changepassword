package com.trailblazers.freewheelers.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotEmpty(message = "Please enter Item Name")
    private String name;

    @DecimalMax("99999")
    @NotNull
    private BigDecimal price;

    @NotNull
    private String description;

    @NotEmpty(message = "Please select Item Type")
    private String type;

    @NotNull
    private Long quantity;

    public Long getItemId() {
        return itemId;
    }
    public Item setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }
    public String getName() {
        return name;
    }
    public Item setName(String name) {
        this.name = name;
        return this;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Item setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public String getDescription() {
        return description;
    }
    public Item setDescription(String description) {
        this.description = description;
        return this;
    }
    public String getType() {
        return type;
    }
    public Item setType(String type) {
        this.type = type;
        return this;
    }
    public Long getQuantity() {
        return quantity;
    }
    public Item setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

}
