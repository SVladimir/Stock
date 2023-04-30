package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.IbmStockExtendHistory;
import com.ex.services.upload.model.MsftStockExtendHistory;
import java.time.LocalDate;
import java.util.Optional;

@StockNameExtend("MSFT")
public interface MsftStockExtendHistoryRepository extends
    StockHistoryExtendRepository<MsftStockExtendHistory> {
  @Override
  default Class<MsftStockExtendHistory> getStockHistoryEntityClass() {
    return MsftStockExtendHistory.class;
  }
  Optional<MsftStockExtendHistory> findByDate(LocalDate date);
}
