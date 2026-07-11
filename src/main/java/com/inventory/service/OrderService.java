package com.inventory.service;

import com.inventory.dao.OrderDao;
import com.inventory.dao.ProductDao;
import com.inventory.exception.InsufficientStockException;
import com.inventory.model.Order;
import com.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for Order.
 * This is where the main business logic of the project lives:
 *  - placing an order reduces product stock
 *  - cancelling an order restores product stock
 */
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    public int placeOrder(Order order) {
        // Step 1: find the product being ordered
        Product product = productDao.getProductById(order.getProductId());

        if (product == null) {
            throw new RuntimeException("Product not found with id: " + order.getProductId());
        }

        // Step 2: check if enough stock is available
        if (product.getQuantity() < order.getQuantity()) {
            throw new InsufficientStockException(
                    "Not enough stock available for product: " + product.getName());
        }

        // Step 3: reduce stock quantity
        int updatedQuantity = product.getQuantity() - order.getQuantity();
        productDao.updateStock(product.getId(), updatedQuantity);

        // Step 4: save the order with status PLACED
        order.setStatus("PLACED");
        return orderDao.placeOrder(order);
    }

    public void cancelOrder(int orderId) {
        Order order = orderDao.getOrderById(orderId);

        if (order == null) {
            throw new RuntimeException("Order not found with id: " + orderId);
        }

        if (order.getStatus().equals("CANCELLED")) {
            throw new RuntimeException("Order is already cancelled");
        }

        // Restore the stock back to the product
        Product product = productDao.getProductById(order.getProductId());
        int restoredQuantity = product.getQuantity() + order.getQuantity();
        productDao.updateStock(product.getId(), restoredQuantity);

        // Mark the order as cancelled
        orderDao.updateOrderStatus(orderId, "CANCELLED");
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }
}
