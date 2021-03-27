package com.oma.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class DBCleaner {


    SessionFactory sessionFactory;

    private static String[] tableNames;

    public void cleanDB(){
        Session session = sessionFactory.openSession();
        for(String tableName : tableNames){
            session.beginTransaction();
            session.createQuery("delete " + tableName).executeUpdate();
            session.getTransaction().commit();
        }
        session.close();
    }

    public void setTableNames(String[] array){
        tableNames = array;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }



}
