package com.oma.services;

import com.itextpdf.text.DocumentException;
import com.oma.model.User;
import com.oma.services.pdf.PdfGenerator;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

public class AbstractService extends User {

    PdfGenerator pdfGenerator;
    User user;

    ByteArrayInputStream getPdfReport(long id)
        throws FileNotFoundException, DocumentException {
        return pdfGenerator.omaToPdf(user);
    }
}
