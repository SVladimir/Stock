package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.AaplStockExtendHistory;
import com.ex.services.upload.model.EpamStockExtendHistory;
import com.ex.services.upload.model.MsftStockExtendHistory;
import java.time.LocalDate;
import java.util.Optional;

@StockNameExtend("EPAM")
public interface EpamStockExtendHistoryRepository extends
    StockHistoryExtendRepository<EpamStockExtendHistory> {
  @Override
  default Class<EpamStockExtendHistory> getStockHistoryEntityClass() {
    return EpamStockExtendHistory.class;
  }
}
