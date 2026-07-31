package com.example.inventory_service.events;

public record QuantityUpdatedEvent(String productId, int quantity) {
    
}
