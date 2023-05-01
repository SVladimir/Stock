package com.ex.services.upload.service;

import com.ex.services.upload.factory.StockHistoryRepositoryFactory;
import com.ex.services.upload.model.stock.StockHistory;
import com.ex.services.upload.repository.stock.StockHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockHistoryService {

  private final StockHistoryRepositoryFactory stockHistoryRepositoryFactory;

  @Autowired
  public StockHistoryService(StockHistoryRepositoryFactory stockHistoryRepositoryFactory) {
    this.stockHistoryRepositoryFactory = stockHistoryRepositoryFactory;
  }

  public <T extends StockHistory> void saveAll(String stockName, List<T> stockHistories) {
    StockHistoryRepository<T> repository = stockHistoryRepositoryFactory.getRepository(stockName);
    if (repository != null) {
      repository.saveAll(stockHistories);
    } else {
      throw new RuntimeException("No repository found for stockName: " + stockName);
    }
  }
  public <T extends StockHistory> void deleteAll(String stockName) {
    StockHistoryRepository<T> repository = stockHistoryRepositoryFactory.getRepository(stockName);
    if (repository != null) {
      repository.deleteAll();
    } else {
      throw new RuntimeException("No repository found for stockName: " + stockName);
    }
  }
}