package com.ex.services.upload.factory;

import com.ex.services.upload.model.stock.AaplStockHistory;
import com.ex.services.upload.model.stock.EpamStockHistory;
import com.ex.services.upload.model.stock.IbmStockHistory;
import com.ex.services.upload.model.stock.MsftStockHistory;
import com.ex.services.upload.model.stock.StockHistory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockHistoryFactoryTest {

  @Test
  public void testCreateStockHistory() {
    StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();

    StockHistory aaplStockHistory = stockHistoryFactory.createStockHistory("AAPL");
    assertTrue(aaplStockHistory instanceof AaplStockHistory, "Expected instance of AaplStockHistory");

    StockHistory msftStockHistory = stockHistoryFactory.createStockHistory("MSFT");
    assertTrue(msftStockHistory instanceof MsftStockHistory, "Expected instance of MsftStockHistory");

    StockHistory ibmStockHistory = stockHistoryFactory.createStockHistory("IBM");
    assertTrue(ibmStockHistory instanceof IbmStockHistory, "Expected instance of IbmStockHistory");

    StockHistory epamStockHistory = stockHistoryFactory.createStockHistory("EPAM");
    assertTrue(epamStockHistory instanceof EpamStockHistory, "Expected instance of EpamStockHistory");
  }

  @Test
  public void testCreateStockHistoryThrowsExceptionForUnsupportedStockName() {
    StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> stockHistoryFactory.createStockHistory("UNSUPPORTED"));
    assertEquals("Unsupported stock name: UNSUPPORTED", exception.getMessage());
  }
}