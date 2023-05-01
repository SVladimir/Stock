package com.ex.services.upload.repository.extend;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.extend.IbmStockExtendHistory;

@StockNameExtend("IBM")
public interface IbmStockExtendHistoryRepository extends
    StockHistoryExtendRepository<IbmStockExtendHistory> {
  @Override
  default Class<IbmStockExtendHistory> getStockHistoryEntityClass() {
    return IbmStockExtendHistory.class;
  }
}
