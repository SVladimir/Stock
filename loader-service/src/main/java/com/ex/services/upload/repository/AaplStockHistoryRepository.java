package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.AaplStockHistory;

@StockName("AAPL")
public interface AaplStockHistoryRepository extends StockHistoryRepository<AaplStockHistory> {

}
