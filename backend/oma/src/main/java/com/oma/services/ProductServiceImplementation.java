package com.oma.services;

import com.oma.dao.ProductDAO;
import com.oma.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImplementation.class.getName());

    @Autowired
    ProductDAO productDAO;

    @Override
    @Transactional
    public void saveProduct(Product product) {
        logger.warn("Save product from service layer");
        productDAO.save(product);
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        logger.info("Get all the product list from service layer");
        return productDAO.getAll();
    }

    @Override
    @Transactional
    public Product getProductByID(long id) {
        logger.info("Get product by id from service layer");
        return productDAO.getByID(id);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        logger.warn("Update product by id from service layer");
        productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        logger.warn("Remove product by id from service layer");
        productDAO.remove(id);
    }

    @Override
    @Transactional
    public Product getProductByCatNumber(String catalogId) {
        logger.info("Get product by catalog number from service layer");
        return productDAO.getByCatNumber(catalogId);
    }
}
