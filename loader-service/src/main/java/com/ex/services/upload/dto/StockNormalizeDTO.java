package com.ex.services.upload.dto;

import java.math.BigDecimal;

public record StockNormalizeDTO(
    String stockName,
    BigDecimal normalize) {

}
