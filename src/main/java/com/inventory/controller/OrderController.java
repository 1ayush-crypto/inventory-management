package com.inventory.controller;

import com.inventory.model.Order;
import com.inventory.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Order.
 * Exposes public APIs under /orders
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place a new order
    // POST /orders
    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        int id = orderService.placeOrder(order);
        return "Order placed successfully with id: " + id;
    }

    // Cancel an existing order (restores stock)
    // PUT /orders/1/cancel
    @PutMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable int id) {
        orderService.cancelOrder(id);
        return "Order with id " + id + " has been cancelled";
    }

    // Get all orders
    // GET /orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get one order by id
    // GET /orders/1
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }
}
