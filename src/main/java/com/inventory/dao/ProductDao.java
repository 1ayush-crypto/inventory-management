package com.inventory.dao;

import com.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * DAO = Data Access Object.
 * This class talks directly to the database using JdbcTemplate.
 * All SQL queries related to "product" table live here.
 */
@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Adds a new product and returns the generated id
    public int addProduct(Product product) {
        String sql = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    // Returns all products in inventory
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                )
        );
    }

    // Returns one product by its id, or null if not found
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> result = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ), id);

        return result.isEmpty() ? null : result.get(0);
    }

    // Updates the stock quantity of a product (used when order is placed/cancelled)
    public void updateStock(int productId, int newQuantity) {
        String sql = "UPDATE product SET quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, newQuantity, productId);
    }
}
