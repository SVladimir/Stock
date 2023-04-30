package com.ex.services.upload.factory;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.AaplStockHistory;
import com.ex.services.upload.model.EpamStockHistory;
import com.ex.services.upload.model.IbmStockHistory;
import com.ex.services.upload.model.MsftStockHistory;
import com.ex.services.upload.model.StockHistory;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class StockHistoryFactory {

  public StockHistoryFactory() {
  }

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