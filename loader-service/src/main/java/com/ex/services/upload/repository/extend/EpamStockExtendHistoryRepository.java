package com.ex.services.upload.repository.extend;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.extend.EpamStockExtendHistory;

@StockNameExtend("EPAM")
public interface EpamStockExtendHistoryRepository extends
    StockHistoryExtendRepository<EpamStockExtendHistory> {
  @Override
  default Class<EpamStockExtendHistory> getStockHistoryEntityClass() {
    return EpamStockExtendHistory.class;
  }
}
