package com.ex.services.upload.repository.extend;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.AaplStockExtendHistory;

@StockNameExtend("AAPL")
public interface AaplStockExtendHistoryRepository extends
    StockHistoryExtendRepository<AaplStockExtendHistory> {

  @Override
  default Class<AaplStockExtendHistory> getStockHistoryEntityClass() {
    return AaplStockExtendHistory.class;
  }

}
