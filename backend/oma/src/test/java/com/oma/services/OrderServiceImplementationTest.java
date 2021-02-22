package com.oma.services;

import com.oma.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplementationTest {

    @Autowired
    OrderService orderService;
    @Autowired
    CompanyService companyService;
    @Autowired
    PriceService priceService;
    @Autowired
    DeliveryPointService deliveryPointService;
    @Autowired
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        clearDB();
        Company comp = new Company("Company", "900900900", new Address("Street", "ZipCode", "City"));
        User user = new User("Name", "Username", "manager",100100100);
        comp.addUser(user);
        DeliveryPoint deliveryPoint = new DeliveryPoint("PKiN", new Address("Plac Defilad 1", "00-129", "Wrszawa"));
        deliveryPoint.setCreatedBy(user);
        deliveryPoint.setCompany(comp);
        Product product = new Product("G482 1l", "BUG482001", "BUG4822001");
        Price price = new Price(comp, product, 13.50);
        companyService.saveCompany(comp);
        deliveryPointService.saveDeliveryPoint(deliveryPoint);
        priceService.savePrice(price);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldSaveOrder() {

        //  given
        ProductsOrder order = new ProductsOrder();
        List<OrderItem> basket = new ArrayList<>();

        //  when
        Company company = companyService
                .getAllCompany()
                .get(0);
        User user = companyService
                .getUsersForCompany(company.getId())
                .stream().filter(x->x.getPrivileges().equals(UserPrivileges.manager))
                .findAny().get();
        DeliveryPoint deliveryPoint = deliveryPointService
                .getGetDeliveryPointsForUser(user.getId())
                .get(0);
        List<Price> prices = priceService
                .getPrices()
                .stream().filter(x->x.getCompany().equals(company))
                .collect(Collectors.toList());
        prices.forEach(x -> basket.add(new OrderItem(x, new Random().nextInt(100))));
        order.setBasket(basket);
        //  then
        orderService.saveOrder(order);
        ProductsOrder result = orderService.getOrderById(order.getId());
        assertTrue(order.equals(result));


    }

    private void clearDB() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Price").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete Product ").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete User ").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete DeliveryPoint ").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete Company").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete ProductsOrder ").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete OrderItem ").executeUpdate();
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete Address ").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}