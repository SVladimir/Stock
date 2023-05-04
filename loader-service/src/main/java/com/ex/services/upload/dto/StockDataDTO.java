package com.ex.services.upload.dto;

import java.math.BigDecimal;

public record StockDataDTO(
    String stockName,
    BigDecimal oldest,
    BigDecimal newest,
    BigDecimal min,
    BigDecimal max) {

}