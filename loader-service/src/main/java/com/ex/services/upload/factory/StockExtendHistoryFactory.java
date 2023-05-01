package com.ex.services.upload.factory;

import com.ex.services.upload.model.extend.AaplStockExtendHistory;
import com.ex.services.upload.model.extend.EpamStockExtendHistory;
import com.ex.services.upload.model.extend.IbmStockExtendHistory;
import com.ex.services.upload.model.extend.MsftStockExtendHistory;
import com.ex.services.upload.model.extend.StockExtendHistory;
import org.springframework.stereotype.Service;

@Service
public class StockExtendHistoryFactory {

  public StockExtendHistoryFactory() {
  }

  public StockExtendHistory createStockHistory(Class tClass) {
    return switch (tClass.getSimpleName()) {
      case "AaplStockHistory" -> new AaplStockExtendHistory();
      case "MsftStockHistory"-> new MsftStockExtendHistory();
      case "IbmStockHistory" -> new IbmStockExtendHistory();
      case "EpamStockHistory"-> new EpamStockExtendHistory();
      default -> throw new IllegalArgumentException("Unsupported stock name: " + tClass.getName());
    };
  }

}