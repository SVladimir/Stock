package com.ex.services.upload.repository.stock;

import com.ex.services.upload.model.stock.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryRepository<T extends StockHistory> extends JpaRepository<T, Long> {
}