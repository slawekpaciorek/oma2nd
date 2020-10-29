package com.oma.services;

import com.oma.dao.ProductDAO;
import com.oma.model.Product;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductDAO productDAO;

    private final Logger log = LoggerFactory.getLogger(ProductServiceImplementation.class);

    @Override
    @Transactional
    public void saveProduct(Product product) {
        log.trace("[save] Product saved.");
        productDAO.save(product);
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        log.trace("[get all] Get all products.");
        return productDAO.getAll();
    }

    @Override
    @Transactional
    public Product getProductByID(long id) {
        log.trace("[get by ID] Get product by ID.");
        return productDAO.getByID(id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        log.trace("[update] Product updated.");
        productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        log.trace("[delete] Product deleted.");
        productDAO.remove(id);
    }

    @Override
    @Transactional
    public Product getProductByCatNumber(String catalogId) {
        log.trace("[get by No.] Get product by Cat. number.");
        return productDAO.getByCatNumber(catalogId);
    }
}
