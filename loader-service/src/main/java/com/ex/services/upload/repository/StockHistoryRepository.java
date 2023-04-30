package com.ex.services.upload.repository;

import com.ex.services.upload.model.StockHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryRepository<T extends StockHistory> extends JpaRepository<T, Long> {
}