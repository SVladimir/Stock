package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.EpamStockExtendHistory;
import com.ex.services.upload.model.IbmStockExtendHistory;
import com.ex.services.upload.model.MsftStockExtendHistory;
import java.time.LocalDate;
import java.util.Optional;

@StockNameExtend("IBM")
public interface IbmStockExtendHistoryRepository extends
    StockHistoryExtendRepository<IbmStockExtendHistory> {
  @Override
  default Class<IbmStockExtendHistory> getStockHistoryEntityClass() {
    return IbmStockExtendHistory.class;
  }
}
