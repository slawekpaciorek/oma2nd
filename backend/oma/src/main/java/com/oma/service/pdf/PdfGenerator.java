package com.oma.service.pdf;

import com.itextpdf.text;

@Service
public class PdfGenerator {

    private Document getNewDocument(){
        Document document = new Document();
        return document;
    }
}
