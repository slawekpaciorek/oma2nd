package com.oma.services.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PdfGenerator {

    private final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

    private Document getNewDocument(){
        Document document = new Document();
        document.open();
        return document;
    }
}
