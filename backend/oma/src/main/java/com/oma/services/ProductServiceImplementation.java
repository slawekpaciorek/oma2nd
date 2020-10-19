package com.oma.services;

import com.oma.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    ProductDAO productDAO;

}
