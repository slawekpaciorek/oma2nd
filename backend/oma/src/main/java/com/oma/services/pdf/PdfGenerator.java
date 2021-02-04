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
import com.oma.model.Product;
import com.oma.model.ProductList;
import com.oma.model.ProductListRow;
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
    private static String file = "OmaDocument.pdf";


    @Autowired
    public PdfGenerator (PdfDateProvider pdfDateProvider){
        this.pdfDateProvider = pdfDateProvider;
    }

    private Document getNewDocument(){
        logger.info("New pdf document created.");
        Document document = new Document();
        document.open();
        return document;
    }

    public ByteArrayInputStream omaToPdf (User user) throws DocumentException, FileNotFoundException {
        Document document = getNewDocument();
        logger.info("New pdf document printed");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        try {
            document.add(omaHeaderParagraph(user.getName()));
            document.add(valueParagraph());
            document.add(companyParagraph());
            document.add(deliveryParagraph());
            document.add(completedParagraph());
            document.add(detailsParagraph());
        } catch (DocumentException ex){
            logger.warn(" Pdf interrput in omaToPdf");
        }

        document.close();

        return new ByteArrayInputStream(pdfGenerated.toByteArray());
    }

    private Paragraph omaHeaderParagraph(String omaOrder) {
        Paragraph paragraph = new Paragraph();
        logger.info("New paragraph created");
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
        Product product = new Product();
        ProductListRow productListRow = new ProductListRow ();

        PdfPCell lp = new PdfPCell(new Phrase("Lp"));
        lp.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(lp);
        PdfPCell nazwaProduktu = new PdfPCell(new Phrase("Nazwa produktu"));
        lp.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(nazwaProduktu);
        PdfPCell kodProduktu = new PdfPCell(new Phrase("Kod produktu"));
        lp.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(kodProduktu);
        PdfPCell ilosc = new PdfPCell(new Phrase("Ilość"));
        lp.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(ilosc);
        PdfPCell cenaNet = new PdfPCell(new Phrase("Cena Net"));
        lp.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cenaNet);
        PdfPCell wartoscNet = new PdfPCell(new Phrase("Warość Net"));
        lp.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(wartoscNet);

        for (int i = 0; i < product.getId(); i++) {
            table.addCell(String.valueOf(product.getId()));
            table.addCell(product.getName());
            table.addCell(product.getTradeId());
            table.addCell(String.valueOf(productListRow.getQuantity()));
            table.addCell(String.valueOf(productListRow.getPrice()));
            table.addCell(String.valueOf(productListRow.getValue()));
        }

        return table;
    }

}
