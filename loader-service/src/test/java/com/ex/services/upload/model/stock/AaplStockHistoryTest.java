package com.ex.services.upload.model.stock;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AaplStockHistoryTest {

  @Test
  public void testSettersAndGetters() {
    LocalDate date = LocalDate.of(2023, 5, 5);
    BigDecimal openPrice = new BigDecimal("100.00");
    BigDecimal highPrice = new BigDecimal("200.00");
    BigDecimal lowPrice = new BigDecimal("90.00");
    BigDecimal closePrice = new BigDecimal("150.00");
    Long volume = 1000L;

    AaplStockHistory aaplStockHistory = new AaplStockHistory();

    aaplStockHistory.setDate(date);
    aaplStockHistory.setOpenPrice(openPrice);
    aaplStockHistory.setHighPrice(highPrice);
    aaplStockHistory.setLowPrice(lowPrice);
    aaplStockHistory.setClosePrice(closePrice);
    aaplStockHistory.setVolume(volume);

    assertNotNull(aaplStockHistory, "AaplStockHistory object should not be null");
    assertEquals(date, aaplStockHistory.getDate(), "Date should be the same as the one provided in the constructor");
    assertEquals(openPrice, aaplStockHistory.getOpenPrice(), "Open price should be the same as the one provided in the constructor");
    assertEquals(highPrice, aaplStockHistory.getHighPrice(), "High price should be the same as the one provided in the constructor");
    assertEquals(lowPrice, aaplStockHistory.getLowPrice(), "Low price should be the same as the one provided in the constructor");
    assertEquals(closePrice, aaplStockHistory.getClosePrice(), "Close price should be the same as the one provided in the constructor");
    assertEquals(volume, aaplStockHistory.getVolume(), "Volume should be the same as the one provided in the constructor");
  }
}