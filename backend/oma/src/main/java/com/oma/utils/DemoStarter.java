package com.oma.utils;

import com.oma.model.*;
import com.oma.services.*;
import lombok.Data;
import net.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Data
public class DemoStarter {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CompanyService companyService;

    @Autowired
    PriceService priceService;

    @Autowired
    ProductService productService;

    @Autowired
    DeliveryPointService deliveryPointService;

    @Autowired
    ProviderService providerService;

    private Logger logger = LoggerFactory.getLogger("DEMO_STARTER");
    private List<Product> products;

    private boolean isConfigured = false;

    public void setUpDemoDataInDB(){
        clearDB();
        Provider provider = new Provider("Provider Company", "PL" + taxNumber(), new Address("Magnacka", "02-496", "Warszawa",500500500));
        provider.addAdmin(new User("ADMIN", "admin@provider.pl","administrator",100000100));
        providerService.saveProvider(provider);
        List<Company> demoCompanies = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            demoCompanies.add(createDemoCompany(i));
        }

        demoCompanies.forEach(company -> company.getPriceList().forEach( price -> priceService.savePrice(price)));
        for (Company demoCompany : demoCompanies) {
            demoCompany.getUsers().forEach(user -> logger.warn(user.toString()));
        }
        isConfigured = true;
    }

    private Company createDemoCompany(int i) {
        Company company = new Company("Company#" + i, "PL" + taxNumber(), createRandomAddress(i));
        try {
            company.addUser(createUser("manager"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            company.addUser(createUser("operator"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        companyService.saveCompany(company);
        company.getUsers().forEach(user -> user.addDeliveryPoint(createDeliveryPont()));
        company.getUsers().forEach(user -> user.getDeliveryPoints().forEach(deliveryPoint -> company.addDeliveryPoint(deliveryPoint)));
        company.getDeliveryPoints().forEach(deliveryPoint -> deliveryPointService.saveDeliveryPoint(deliveryPoint));
        company.setPriceList(createRandomPriceList(company));
        return company;
    }

    private List<Price> createRandomPriceList(Company company) {
        List<Price> priceList = new ArrayList<>();
        if(products==null){
            products = setDefaultProducts();
        }
        for(Product product : products)
            priceList.add(new Price(company,product,Math.abs(new Random().nextFloat())));
        return  priceList;
    }

    private List<Product> setDefaultProducts() {
        List<Product> products = new ArrayList<>();
        for(int i =0; i < 15; i++){
            Product product = createProduct(i);
            productService.saveProduct(product);
            products.add(product);
        }
        return products;
    }

    private Product createProduct(int counter) {
        return new Product("product#" + counter, "trade#" + counter, "cat#" + counter, "category#" + counter);
    }

    private DeliveryPoint createDeliveryPont() {
        return new DeliveryPoint(RandomString.make(6), createRandomAddress(Math.abs(new Random().nextInt(10))));
    }

    private User createUser(String privileges) throws IOException {
        String name = createRandomName();
        return new User(name, name+"@company.pl",privileges, phoneNumber());
    }

    private Address createRandomAddress(int counter) {
        return new Address("street#" + counter, "00-00" + counter,"City#" + counter, new Random().nextInt(1000000000));
    }

    private String createRandomName() throws IOException {
        String resource = "demo_starter_names";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(resource).getInputStream()));
        List<String> namesList =  reader.lines().collect(Collectors.toList());
        reader.close();
        return namesList.get(Math.abs(new Random().nextInt(namesList.size())));
    }

    private int phoneNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 9; i++){
            stringBuilder.append(Math.abs(new Random().nextInt(10)));
        }
        return Integer.parseInt(stringBuilder.toString());
    }

    private String taxNumber() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<10;i++){
            builder.append(Math.abs(new Random().nextInt(10)));
        }
        return builder.toString();
    }

    private void clearDB(){
        deleteDataFromTables(new String[]{"ProductsOrder", "OrderItem", "Price", "Product", "DeliveryPoint", "Address", "User", "Company"});
    }

    private void deleteDataFromTables(String[] tableNames){
        Session session = sessionFactory.openSession();
        for(String tableName : tableNames){
            session.beginTransaction();
            session.createQuery("delete " + tableName).executeUpdate();
            session.getTransaction().commit();
        }
        session.close();
    }
}
