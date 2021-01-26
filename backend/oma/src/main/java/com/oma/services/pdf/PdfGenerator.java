package com.oma.services.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.oma.model.Company;
import com.oma.model.DeliveryPoint;
import com.oma.model.ProductsOrder;
import com.oma.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

@Service
public class PdfGenerator {

    private final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);
    private PdfDateProvider pdfDateProvider;
    private ByteArrayOutputStream pdfGenerated = new ByteArrayOutputStream();
    private ProductsOrder productsOrder = new ProductsOrder();
    private Company company = new Company();
    private DeliveryPoint deliveryPoint = new DeliveryPoint();
    private User user = new User();


    @Autowired
    public PdfGenerator (PdfDateProvider pdfDateProvider){
        this.pdfDateProvider = pdfDateProvider;
    }

    private Document getNewDocument(){
        Document document = new Document();
        document.open();
        return document;
    }

    public ByteArrayInputStream omaToPdf (User user) throws DocumentException, FileNotFoundException {
        Document document = getNewDocument();
        PdfWriter.getInstance(document, new FileOutputStream("OmaDocument.pdf"));

        document.add(omaHeaderParagraph(user.getName()));
        document.add(valueParagraph());
        document.add(companyParagraph());
        document.add(deliveryParagraph());
        document.add(completedParagraph());
        document.add(detailsParagraph());

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

    private Paragraph valueParagraph(){
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add("Numer zamówienia: " + productsOrder.getId());
        return paragraph;
    }

    private Paragraph companyParagraph(){
        Paragraph paragraph = new Paragraph();
        Chunk companyName = new Chunk("Nazwa Firmy: " + company.getName());
        Chunk companyTax = new Chunk("Dane Firmy (NIP): " + company.getTaxNumberId());
        Chunk companyAddress = new Chunk("Adres Firmy: " + company.getAddress());

        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add("Odbiorca zamówienia: " +
            companyName + companyTax + companyAddress);
        return paragraph;
    }

    private Paragraph deliveryParagraph(){
        Paragraph paragraph = new Paragraph();
        Chunk pointName = new Chunk("Nazwa punktu: " + deliveryPoint.getName());
        Chunk pointAddress = new Chunk("Adres punktu: " + deliveryPoint.getAddress());
        Chunk contactPerson = new Chunk("Osoba kontaktowa (osoba odpowiedzialna za " +
            "stworzenie zamówienia: " + deliveryPoint.getCreatedBy());

        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add("Punkt Dostawy: "
            + pointName + pointAddress + contactPerson);
        return paragraph;
    }

    private Paragraph completedParagraph(){
        Paragraph paragraph = new Paragraph();
        Chunk companyNameMake = new Chunk("Nazwa firmy: " + user.getCompany().getName());
        Chunk companyTaxMake = new Chunk("Dane firmy (NIP): "
            + user.getCompany().getTaxNumberId());
        Chunk companyAddressMake = new Chunk("Adres firmy: "
            + user.getCompany().getAddress());

        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add("Zamówienie zrealizowane przez: " +
            companyNameMake + companyTaxMake + companyAddressMake);
        return paragraph;
    }

    private Paragraph detailsParagraph(){
        Paragraph paragraph = new Paragraph();

        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add("Szczegóły zamówienia: " + orderDetailsTable());
        return paragraph;
    }

    private PdfPTable orderDetailsTable() {
        PdfPTable table = new PdfPTable(PdfConfiguration.COMPANIES_TABLE_COLUMNS_COUNT);
        Stream.of("Lp.", "Nazwa produktu", "Kod produktu", "Ilość", "Cena net", "Wartość net")
            .forEach(columnTitle -> {
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(columnTitle));
                table.addCell(header);
            });
        return table;
    }

    private void addRowsToOrderDetaisTable(PdfPTable pdfPTable){

    }

}