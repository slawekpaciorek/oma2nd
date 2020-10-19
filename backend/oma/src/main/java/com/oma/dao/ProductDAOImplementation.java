package com.oma.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImplementation implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;
}
