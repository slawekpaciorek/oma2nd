package com.oma.services.pdf;

import java.math.BigDecimal;

public class PdfConfiguration {

    static final float DEFAULT_MARGIN_SIZE = 10;
    static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    static final int ROUND_DIGITS_BIG_DECIMAL = 4;
    static final int ROUND_MODE_BIG_DECIMAL = BigDecimal.ROUND_HALF_UP;
    static final int COMPANIES_TABLE_COLUMNS_COUNT = 3;
    static final int PRODUCTS_TABLE_COLUMNS_COUNT = 6;
    static final float TABLE_SPACING = 15;
    static final String BASE_FONT_NAME = "fonts/DejaVuSans.ttf";
    static final int DEFAULT_FONT_SIZE = 10;
    static final int HEADER_DEFAULT_FONT_SIZE = 20;
}
