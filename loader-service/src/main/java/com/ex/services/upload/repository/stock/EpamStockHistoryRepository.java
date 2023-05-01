package com.ex.services.upload.repository.stock;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.stock.EpamStockHistory;

@StockName("EPAM")
public interface EpamStockHistoryRepository extends StockHistoryRepository<EpamStockHistory> {

}
