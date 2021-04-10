package com.oma.prasercsv;

import com.oma.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class WriteCsvProductResponseTest {

    @Autowired
    WriteCsvProductResponse writeCsvProductResponse;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void writeProducts() {
        //given

        //when

        //then
    }

    @Test
    void writeProduct() {
        //given
        Product product = new Product();
        product.setId(1);
        product.setName("ExampleName");
        product.setTradeId("ExampleTrade");
        product.setCatalogId("ExampleCatalogId");
        product.setCategoryName("ExampleCategoryName");
        //when


        //then
    }
}
