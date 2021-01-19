package com.oma.services.pdf;

import com.itextpdf.text.Document;

public class PdfGenerator {

    private Document getNewDocument(){
        Document document = new Document();
        document.open();
        return document;
    }
}
