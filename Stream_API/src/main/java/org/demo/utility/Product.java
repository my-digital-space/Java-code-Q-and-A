package org.demo.utility;

import java.math.BigDecimal;

public class Product {
    private Integer id;
    private BigDecimal price;
    private Integer inventoryCount;

    public Product(Integer id, BigDecimal price, Integer inventoryCount) {
        this.id = id;
        this.price = price;
        this.inventoryCount = inventoryCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", inventoryCount=" + inventoryCount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Integer inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

}
