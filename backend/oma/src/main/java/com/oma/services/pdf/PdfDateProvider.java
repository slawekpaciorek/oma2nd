package com.oma.services.pdf;

import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class PdfDateProvider {

    private DateFormat dateFormat;

    public void PdfDateTimeProvider() {
        dateFormat = new SimpleDateFormat(PdfConfiguration.DATE_FORMAT);
    }

    public String getDateTime() {
        return dateFormat.format(new Date());
    }

}
