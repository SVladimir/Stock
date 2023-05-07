package com.ex.services.upload.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockNormalizeDTOTest {

  @Test
  public void testConstructor() {
    String stockName = "Example Stock";
    BigDecimal normalize = new BigDecimal("100.00");

    StockNormalizeDTO stockNormalizeDTO = new StockNormalizeDTO(stockName, normalize);

    assertNotNull(stockNormalizeDTO, "StockNormalizeDTO object should not be null");
    assertEquals(stockName, stockNormalizeDTO.stockName(), "Stock name should be the same as the one provided in the constructor");
    assertEquals(normalize, stockNormalizeDTO.normalize(), "Normalize value should be the same as the one provided in the constructor");
  }
}
