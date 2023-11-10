//package com.codingcomrades.fullstackbackend.model;
//
//import jakarta.persistence.Embeddable;
//
//@Embeddable
//public class OrderItem {
//    private String itemId;
//    private int quantity;
//
//    // Constructors, getters, and setters
//    public OrderItem() {
//    }
//
//    public OrderItem(String itemId, int quantity) {
//        this.itemId = itemId;
//        this.quantity = quantity;
//    }
//
//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//}


package com.codingcomrades.fullstackbackend.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItem {
    private String productId; // Change 'itemId' to 'productId'
    private int quantity;

    // Constructors, getters, and setters
    public OrderItem() {
    }

    public OrderItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
