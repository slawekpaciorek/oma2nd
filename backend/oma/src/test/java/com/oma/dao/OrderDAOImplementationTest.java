package com.oma.dao;

import com.oma.model.OrderStatus;
import com.oma.model.ProductsOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
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
    OrderDao orderDao;

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @AfterEach
    void tearDown() {
        resetDB();
    }

    @Test
    void shouldSaveOrder() {

        //  given
        ProductsOrder productsOrder = returnDefaultOrder();
        session = returnSession();

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
        if(order.equals(expectedAfterUpdate)){
            shouldUpdateOrder();
        }
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
}