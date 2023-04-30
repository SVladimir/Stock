package com.ex.services.upload.repository;

import com.ex.services.upload.model.StockExtendHistory;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockHistoryExtendRepository<T extends StockExtendHistory> extends
    JpaRepository<T, Long> {

  default Class<T> getStockHistoryEntityClass() {
    return (Class<T>) StockExtendHistory.class;
  }

  Optional<? extends StockExtendHistory> findByDate(LocalDate date);
}