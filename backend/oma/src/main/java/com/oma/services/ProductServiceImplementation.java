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
}
