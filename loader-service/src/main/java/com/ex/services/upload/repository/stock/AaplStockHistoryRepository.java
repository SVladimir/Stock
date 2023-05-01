package com.ex.services.upload.repository.stock;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.stock.AaplStockHistory;

@StockName("AAPL")
public interface AaplStockHistoryRepository extends StockHistoryRepository<AaplStockHistory> {

}
