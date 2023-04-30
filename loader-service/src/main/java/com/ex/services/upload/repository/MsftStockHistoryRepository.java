package com.ex.services.upload.repository;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.MsftStockHistory;

@StockName("MSFT")
public interface MsftStockHistoryRepository extends StockHistoryRepository<MsftStockHistory> {

}