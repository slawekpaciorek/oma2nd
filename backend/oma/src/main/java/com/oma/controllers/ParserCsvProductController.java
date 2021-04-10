package com.oma.controllers;

import com.oma.dao.ProductDAO;
import com.oma.model.Product;
import com.oma.prasercsv.WriteCsvProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ParserCsvProductController {

    @Autowired
    ProductDAO productDAO;

    @RequestMapping(value = "/products", produces = "text/csv")
    public void findProducts(HttpServletResponse response) throws IOException {
        List<Product> products = productDAO.getAll();
        WriteCsvProductResponse.writeProducts(response.getWriter(), products);
    }

    @RequestMapping(value = "/products/{id}")
    public void findUser(@PathVariable long id, HttpServletResponse response) throws IOException {
        Product product = productDAO.getByID(id);
        WriteCsvProductResponse.writeProduct(response.getWriter(), product);
    }
}
