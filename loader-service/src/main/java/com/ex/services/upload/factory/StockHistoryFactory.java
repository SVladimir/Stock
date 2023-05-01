package com.ex.services.upload.factory;

import com.ex.services.upload.model.stock.AaplStockHistory;
import com.ex.services.upload.model.stock.EpamStockHistory;
import com.ex.services.upload.model.stock.IbmStockHistory;
import com.ex.services.upload.model.stock.MsftStockHistory;
import com.ex.services.upload.model.stock.StockHistory;
import org.springframework.stereotype.Service;

@Service
public class StockHistoryFactory {


  public StockHistory createStockHistory(String stockName) {
    return switch (stockName) {
      case "AAPL" -> new AaplStockHistory();
      case "MSFT" -> new MsftStockHistory();
      case "IBM" -> new IbmStockHistory();
      case "EPAM" -> new EpamStockHistory();
      default -> throw new IllegalArgumentException("Unsupported stock name: " + stockName);
    };
  }

}