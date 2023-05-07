package com.ex.services.upload.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockSummaryDTOTest {

  @Test
  public void testConstructor() {
    String stockName = "Example Stock";
    BigDecimal oldest = new BigDecimal("100.00");
    BigDecimal newest = new BigDecimal("200.00");
    BigDecimal min = new BigDecimal("90.00");
    BigDecimal max = new BigDecimal("210.00");
    BigDecimal normalize = new BigDecimal("150.00");

    StockSummaryDTO stockSummaryDTO = new StockSummaryDTO(stockName, oldest, newest, min, max, normalize);

    assertNotNull(stockSummaryDTO, "StockSummaryDTO object should not be null");
    assertEquals(stockName, stockSummaryDTO.stockName(), "Stock name should be the same as the one provided in the constructor");
    assertEquals(oldest, stockSummaryDTO.oldest(), "Oldest value should be the same as the one provided in the constructor");
    assertEquals(newest, stockSummaryDTO.newest(), "Newest value should be the same as the one provided in the constructor");
    assertEquals(min, stockSummaryDTO.min(), "Min value should be the same as the one provided in the constructor");
    assertEquals(max, stockSummaryDTO.max(), "Max value should be the same as the one provided in the constructor");
    assertEquals(normalize, stockSummaryDTO.normalize(), "Normalize value should be the same as the one provided in the constructor");
  }
}