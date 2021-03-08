package com.oma.dao;

import com.oma.model.User;
import com.oma.model.OrderStatus;
import com.oma.model.ProductsOrder;
import com.oma.services.UserService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDAOImplementationTest {

    @Autowired
    UserService userService;

    @Autowired
    OrderDao orderDao;

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @BeforeEach
    void setUp() {
        resetDB();
    }

    @Test
    void shouldSaveOrder() {

        ProductsOrder productsOrder = returnDefaultOrder();
        //  given
        session = returnSession();
        User user = new User(getDefaultString(), getDefaultString(),"manager", 100100100);
        userService.addUser(user);
        productsOrder.setCreatedBy(user);


        //  when
        orderDao.saveOrder(productsOrder);
        session.beginTransaction();
        List<ProductsOrder> orders = session.createQuery("from ProductsOrder", ProductsOrder.class).list();
        session.getTransaction().commit();
        session.close();

        //  then
        assertTrue(orders.contains(productsOrder));
    }

    @Test
    void shouldGetOrderById() {

        //  given
        ProductsOrder order = returnDefaultOrder();

        //  when
        orderDao.saveOrder(order);
        long id = order.getId();
        ProductsOrder result = orderDao.getOrderById(id);

        //  then
        assertEquals(order, result);


    }

    @Test
    void shouldGetOrderList() {

        //  given
        ProductsOrder[] orders = new ProductsOrder[300];

        //  when
        for(int i = 0; i < orders.length; i++){
            orders[i] = returnDefaultOrder();
            orderDao.saveOrder(orders[i]);
        }
        List<ProductsOrder> resultList = orderDao.getOrderList();

        //  then
        for (ProductsOrder order : orders){
            assertTrue(resultList.contains(order));
        }

    }

    @Test
    void shouldUpdateOrder() {

        //  given
        ProductsOrder order = returnDefaultOrder();
        ProductsOrder expectedAfterUpdate = returnDefaultOrder();

        //  when
        expectedAfterUpdate.setStatus(OrderStatus.approved);
        orderDao.saveOrder(order);
        long id = order.getId();
        orderDao.updateOrder(id, expectedAfterUpdate);
        ProductsOrder result = orderDao.getOrderById(id);

        //  then
        assertEquals(expectedAfterUpdate, result);

    }

    @Test
    void shouldRemoveOrder() {

        //  given
        ProductsOrder order = returnDefaultOrder();

        //  when
        orderDao.saveOrder(order);
        long id = order.getId();
        orderDao.removeOrder(id);

        //  then
        assertThrows(EmptyResultDataAccessException.class, ()->orderDao.getOrderById(id));


    }

    private void resetDB(){
        cleanTable("DeliveryPoint");
        cleanTable("ProductsOrder");
        cleanTable("User");
    }

    private void cleanTable(String tableName) {
        session = returnSession();
        session.beginTransaction();
        session.createQuery("delete " + tableName).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private Session returnSession() {
        return sessionFactory.openSession();
    }

    private ProductsOrder returnDefaultOrder() {
        return new ProductsOrder(LocalDate.now(), OrderStatus.not_approved,"info about order");
    }

    private String getDefaultString() {
        return new RandomString().nextString();
    }
}