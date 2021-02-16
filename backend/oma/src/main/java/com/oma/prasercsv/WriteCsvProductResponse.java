package com.oma.prasercsv;

import com.oma.model.Product;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.List;

public class WriteCsvProductResponse {

    private static final Logger logger = LoggerFactory.getLogger(WriteCsvProductResponse.class);

    public static void writeProducts(PrintWriter writer, List<Product> productList){
        try {
            ColumnPositionMappingStrategy<Product> mappingStrategy = new ColumnPositionMappingStrategy<>();
            mappingStrategy.setType(Product.class);

            String[] columns = new String[]{"id","name","tradeId","catalogId","categoryName"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Product> beanToCsv = new StatefulBeanToCsvBuilder<Product>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mappingStrategy)
                    .withSeparator(',')
                    .build();

            beanToCsv.write(productList);

        }catch (CsvException csvException) {
            logger.error("Error mapping Bean to CSV",csvException);
        }
    }
}
