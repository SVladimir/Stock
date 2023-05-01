package com.ex.services.upload.repository.stock;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.IbmStockHistory;

@StockName("IBM")
public interface IbmStockHistoryRepository extends StockHistoryRepository<IbmStockHistory> {

}
