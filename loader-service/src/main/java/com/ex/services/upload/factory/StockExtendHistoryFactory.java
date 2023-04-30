package com.ex.services.upload.factory;

import com.ex.services.upload.model.AaplStockExtendHistory;
import com.ex.services.upload.model.AaplStockHistory;
import com.ex.services.upload.model.EpamStockExtendHistory;
import com.ex.services.upload.model.EpamStockHistory;
import com.ex.services.upload.model.IbmStockExtendHistory;
import com.ex.services.upload.model.IbmStockHistory;
import com.ex.services.upload.model.MsftStockExtendHistory;
import com.ex.services.upload.model.MsftStockHistory;
import com.ex.services.upload.model.StockExtendHistory;
import com.ex.services.upload.model.StockHistory;
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