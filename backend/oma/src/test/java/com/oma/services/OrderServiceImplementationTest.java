package com.oma.services;

import com.oma.model.*;
import com.oma.utils.DBCleaner;
import net.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private List<Product> products;

    @BeforeEach
    void setUp() {

        clearDB();
        setProductsForTest();
        int arrayLength = returnPositiveIntHigherThanThreeAndLessThanBoundary(5);

        List<Company> companies = new ArrayList<>();
        for(int i = 0; i < arrayLength; i++){
            companies.add(generateRandomCompany());
        }

        List<User> users = new ArrayList<>();
        for (int i = 0;i < arrayLength; i++){
            users.add(generateRandomUser());
            companies.get(i).addUser(users.get(i));
        }

        List<DeliveryPoint> deliveryPoints = new ArrayList<>();

        for(int i = 0; i < arrayLength*arrayLength; i++){
            deliveryPoints.add(generateRandomDeliveryPoint());
        }

        for(int i = deliveryPoints.size()-1; i >= 0 ; i = i - arrayLength){
            for(int j = i; j > i - arrayLength; j--){
                deliveryPoints.get(j).setCreatedBy(users.get(i/arrayLength));
            }
        }

        List<Price> priceList = new ArrayList<>();

        for(Company company : companies){
            for(Product product : products){
                priceList.add(new Price(company, product, Math.abs(new Random().nextDouble())));
            }
        }

        companies.forEach(x -> companyService.saveCompany(x));
        deliveryPoints.forEach(x -> deliveryPointService.saveDeliveryPoint(x));
        priceList.forEach(x -> priceService.savePrice(x));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldSaveOrder() {

        //  given
        ProductsOrder order = new ProductsOrder();
        List<OrderItem> basket = new ArrayList<>();
        List<Company> companies = companyService.getAllCompany();
        Company company = companies.get(Math.abs(new Random().nextInt(companies.size())));
        List<User> users = companyService.getUsersForCompany(company.getId());
        User user = users.get(Math.abs(new Random().nextInt(users.size())));
        List<DeliveryPoint> deliveryPoints = deliveryPointService.getGetDeliveryPointsForUser(user.getId());
        DeliveryPoint deliveryPoint = deliveryPoints.get(Math.abs(new Random().nextInt(deliveryPoints.size())));
        List<Price> priceList = priceService.getPrices().stream().filter((x->x.getCompany().equals(company))).collect(Collectors.toList());

//        when
        for(Price price : priceList){
            basket.add(new OrderItem(price, new Random().nextInt(10)));
        }
        order.setBasket(basket);
        order.setCompany(company);
        order.setCreatedBy(user);
        order.setInfo("new order");
        order.setDeliveryPoint(deliveryPoint);
        order.setCreatedAt(LocalDate.now());

        orderService.saveOrder(order);
        List<ProductsOrder> resultList = orderService.getOrders();

//        then
        assertTrue(resultList.contains(order));
    }

    @Test
    void shouldSaveOrders(){
//        given
        List<Company> companies = companyService.getAllCompany();
        List<ProductsOrder> expected = new ArrayList<>();

//        when
        for(Company company : companies){
            ProductsOrder order = new ProductsOrder();
            List<OrderItem> basket = new ArrayList<>();
            List<User> users = companyService.getUsersForCompany(company.getId());
            User randomUser = users.get(Math.abs(new Random().nextInt(users.size())));
            List<DeliveryPoint> deliveryPoints = deliveryPointService.getGetDeliveryPointsForUser(randomUser.getId());
            DeliveryPoint deliveryPoint = deliveryPoints.get(Math.abs(new Random().nextInt(deliveryPoints.size())));
            List<Price> priceList = priceService.getPrices().stream().filter(x->x.getCompany().equals(company)).collect(Collectors.toList());
            for(Price product : priceList){
                basket.add(new OrderItem(product, Math.abs(new Random().nextInt(10))));
            }
            order.setCreatedAt(LocalDate.now());
            order.setCreatedBy(randomUser);
            order.setCompany(company);
            order.setDeliveryPoint(deliveryPoint);
            order.setBasket(basket);
            order.setInfo("Orderd for " + order.getCompany().getName() +", createdBy : " + order.getCreatedBy().getUsername() + ", delivery point : " + order.getDeliveryPoint().getName() + ", summary value : " + order.getSummaryValue());

            orderService.saveOrder(order);
            expected.add(order);
        }

        List<ProductsOrder> result = orderService.getOrders();

//        then
        assertEquals(expected, result);
    }

    private void clearDB() {
        DBCleaner dbCleaner = new DBCleaner();
        dbCleaner.setSessionFactory(sessionFactory);
        dbCleaner.setTableNames(new String[]{"Price", "Product", "User", "DeliveryPoint", "Company", "ProductsOrder", "OrderItem", "Address"});
        dbCleaner.cleanDB();
    }

    private void clearTable(Session session, String tableName){
        session.beginTransaction();
        session.createQuery("delete " + tableName).executeUpdate();
        session.getTransaction().commit();
    }

    private Price createRandomPrice(Product product, Company company){
        return new Price(company, product, new Random().nextInt(20));
    }

    private void setProductsForTest(){
        products = new ArrayList<>();
        products.add(new Product("Plybuz 1l T201", "BUT201", "BUT201001", "chemia prof"));
        products.add(new Product("Perfekt 1l G444", "BUG444", "BUG444001", "chemia prof"));
        products.add(new Product("Aktiv 1l G433", "BUG433", "BUG433001", "chemia prof"));
        products.add(new Product("Bucasan Trendy 1l T464", "BUT464", "BUT464001", "chemia prof"));
        products.add(new Product("Erol 1l G490", "BUG490", "BUG490001", "chemia prof"));
        products.add(new Product("Erol Cid 1l G491", "BUG491", "BUG491001", "chemia prof"));
        products.add(new Product("Metapol G505", "BUG505", "BUG5050006", "chemia prof"));
        products.add(new Product("Metasoft G506", "BUG506", "BUG5060006", "chemia prof"));
        products.add(new Product("Vario Clean 1l T560", "BUT560", "BUT560001", "chemia prof"));
        products.add(new Product("Bistro G435", "BUG435", "BUG435001", "chemia prof"));
        products.add(new Product("Profiglass 600ml G522", "BUG522", "BUG5220006", "chemia prof"));
        products.add(new Product("Buzwindowmaster 1l G525", "BUG525", "BUG235001", "chemia prof"));
    }

    private Company generateRandomCompany(){
        return new Company("company-" + generateRandomString(), "taxId-" + generateRandomString(),generateRandomAddress());
    }

    private Address generateRandomAddress() {
        return new Address("street-" + generateRandomString(), "code-" + generateRandomString(), "city-" + generateRandomString());
    }

    private User generateRandomUser(){
        return new User("name-" + generateRandomString(), "user-" + generateRandomString(), "manager", Math.abs(new Random().nextInt(1000000000)));
    }

    private DeliveryPoint generateRandomDeliveryPoint() {
        return new DeliveryPoint("name-" + generateRandomString(), generateRandomAddress());
    }

    private String generateRandomString(){

        return RandomString.make(10);

    }

    private int returnPositiveIntHigherThanThreeAndLessThanBoundary(int boundary){
        int result = 0;
        while(result < 3){
            result = Math.abs(new Random().nextInt(boundary));
        }
        return result;
    }
}