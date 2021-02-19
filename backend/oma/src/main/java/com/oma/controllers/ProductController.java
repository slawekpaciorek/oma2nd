package com.oma.controllers;

import com.oma.model.Product;
import com.oma.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Product> displayAllProducts(){
        return productService.getProducts();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addProduct(@RequestBody Product product){
        productService.saveProduct(product);
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public void updateProduct(@RequestParam("id") String id, @RequestBody Product product){
        productService.updateProduct(Long.parseLong(id), product);
    }

    @DeleteMapping(value = "/remove")
    public void removeProduct(@RequestParam("id") String id){
        productService.deleteProduct(Long.parseLong(id));
    }
}
