package com.oma.services;

import com.oma.dao.ProductDAO;
import com.oma.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productDAO.getAll();
    }

    @Override
    @Transactional
    public Product getProductByID(long id) {
        return productDAO.getByID(id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        productDAO.remove(id);
    }

    @Override
    @Transactional
    public Product getProductByCatNumber(String catalogId) {
        return productDAO.getByCatNumber(catalogId);
    }
}
