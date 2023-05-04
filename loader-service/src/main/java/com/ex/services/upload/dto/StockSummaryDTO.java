package com.ex.services.upload.dto;

import java.math.BigDecimal;

public record StockSummaryDTO(

    String stockName,
    BigDecimal oldest,
    BigDecimal newest,
    BigDecimal min,
    BigDecimal max,
    BigDecimal normalize) {

}
