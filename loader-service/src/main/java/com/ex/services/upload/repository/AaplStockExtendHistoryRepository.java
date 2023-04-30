package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.AaplStockExtendHistory;
import java.time.LocalDate;
import java.util.Optional;

@StockNameExtend("AAPL")
public interface AaplStockExtendHistoryRepository extends
    StockHistoryExtendRepository<AaplStockExtendHistory> {

  @Override
  default Class<AaplStockExtendHistory> getStockHistoryEntityClass() {
    return AaplStockExtendHistory.class;
  }

  Optional<AaplStockExtendHistory> findByDate(LocalDate date);
}
