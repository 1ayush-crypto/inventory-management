package com.inventory.dao;

import com.inventory.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * DAO class for the "orders" table.
 * Keeps all order-related SQL queries in one place.
 */
@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Inserts a new order and returns its generated id
    public int placeOrder(Order order) {
        String sql = "INSERT INTO orders (product_id, quantity, status) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getProductId());
            ps.setInt(2, order.getQuantity());
            ps.setString(3, order.getStatus());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // Returns all orders
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Order(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getString("status"),
                        rs.getTimestamp("order_date")
                )
        );
    }

    // Returns a single order by id, or null if not found
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        List<Order> result = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Order(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getString("status"),
                        rs.getTimestamp("order_date")
                ), id);

        return result.isEmpty() ? null : result.get(0);
    }

    // Updates the status of an order (e.g. PLACED -> CANCELLED)
    public void updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, orderId);
    }
}
