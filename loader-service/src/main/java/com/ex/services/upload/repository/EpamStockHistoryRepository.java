package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.EpamStockHistory;

@StockName("EPAM")
public interface EpamStockHistoryRepository extends StockHistoryRepository<EpamStockHistory> {

}
