package com.ex.services.upload.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockDataDTOTest {

  @Test
  public void testStockDataDTO() {
    // Create a new StockDataDTO instance
    StockDataDTO stockData = new StockDataDTO(
        "Sample Stock",
        new BigDecimal("100.00"),
        new BigDecimal("110.00"),
        new BigDecimal("90.00"),
        new BigDecimal("120.00")
    );

    // Check that the fields are initialized correctly
    assertEquals("Sample Stock", stockData.stockName());
    assertEquals(new BigDecimal("100.00"), stockData.oldest());
    assertEquals(new BigDecimal("110.00"), stockData.newest());
    assertEquals(new BigDecimal("90.00"), stockData.min());
    assertEquals(new BigDecimal("120.00"), stockData.max());
  }
}