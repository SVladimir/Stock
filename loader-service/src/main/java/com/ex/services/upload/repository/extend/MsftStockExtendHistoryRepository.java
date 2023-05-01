package com.ex.services.upload.repository.extend;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.extend.MsftStockExtendHistory;

@StockNameExtend("MSFT")
public interface MsftStockExtendHistoryRepository extends
    StockHistoryExtendRepository<MsftStockExtendHistory> {
  @Override
  default Class<MsftStockExtendHistory> getStockHistoryEntityClass() {
    return MsftStockExtendHistory.class;
  }

}
