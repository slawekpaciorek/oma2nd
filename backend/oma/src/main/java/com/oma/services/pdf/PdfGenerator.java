package com.oma.services.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.oma.model.Company;
import com.oma.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.Stream;

@Service
public class PdfGenerator {

    private final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);
    private PdfDateProvider pdfDateProvider;
    private ByteArrayOutputStream pdfGenerated = new ByteArrayOutputStream();

    @Autowired
    public PdfGenerator (PdfDateProvider pdfDateProvider){
        this.pdfDateProvider = pdfDateProvider;
    }

    private Document getNewDocument(){
        Document document = new Document();
        document.open();
        return document;
    }

    public ByteArrayInputStream omaToPdf (User user) throws DocumentException {
        Document document = getNewDocument();

        document.add(omaHeaderParagraph(user.getName()));
        document.close();

        return new ByteArrayInputStream(pdfGenerated.toByteArray());
    }

    private Paragraph omaHeaderParagraph(String omaOrder) {
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(new Chunk("Order: "));
        paragraph.add(new Chunk(omaOrder));
        return paragraph;
    }

    private Paragraph valueParagraph(String value){
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(new Chunk(value));
        return paragraph;
    }

    private PdfPTable getCompanyTable(Company company) {
        PdfPTable table = new PdfPTable(PdfConfiguration.COMPANIES_TABLE_COLUMNS_COUNT);
        
        table.addCell("Company");

        return table;
    }
}
