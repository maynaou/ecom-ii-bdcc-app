package com.example.billing_service.events;

public record QuantityUpdatedEvent(String productId, int quantity) {
}
