package com.oma.services;

import com.sun.org.slf4j.internal.LoggerFactory;

import javax.swing.text.Document;
import java.io.ByteArrayInputStream;

@Service
public class PdfGenerator {

    private final LoggerFactory logger = LoggerFactory.getLogger(PdfGenerator.class);

    public ByteArrayInputStream invoiceToPdf() {
        Document document = getNewDocument();
        return null;
    }

    private Document getNewDocument() {
        return null;
    }

    private Paragraph getInvoiceHeaderParagraph() {
        return null;
    }

    private Paragraph getPropertyValueParagraph() {
        return null;
    }

    private PdfPTable getCompanyTable() {
        return null;
    }

    private void addTableHeader() {
    }

    private PdfPCell getHeaderCell() {
        return null;
    }

    private void addRowsCompanyTable() {
    }

    private PdfPTable getProductsTable() {
        return null;
    }

    private void addRowsProductTable() {
    }

}
