package com.inventory.service;

import com.inventory.dao.ProductDao;
import com.inventory.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for Product.
 * Controllers call this class instead of calling the DAO directly.
 * This keeps business logic separate from database code (separation of concerns).
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }
}
