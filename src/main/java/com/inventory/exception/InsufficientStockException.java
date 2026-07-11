package com.inventory.exception;

/**
 * Thrown when a customer tries to order more quantity than
 * what is currently available in stock.
 * This is a custom exception - shows understanding of Java exception handling.
 */
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }
}
