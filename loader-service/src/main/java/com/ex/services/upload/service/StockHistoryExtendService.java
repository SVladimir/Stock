package com.ex.services.upload.service;

import com.ex.services.upload.factory.StockExtendHistoryRepositoryFactory;
import com.ex.services.upload.model.StockExtendHistory;
import com.ex.services.upload.model.StockHistory;
import com.ex.services.upload.repository.extend.StockHistoryExtendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockHistoryExtendService {

  private final StockExtendHistoryRepositoryFactory stockExtendHistoryRepositoryFactory;

  @Autowired
  public StockHistoryExtendService(
      StockExtendHistoryRepositoryFactory stockExtendHistoryRepositoryFactory) {
    this.stockExtendHistoryRepositoryFactory = stockExtendHistoryRepositoryFactory;
  }

  public <T extends StockExtendHistory> void save(T stockExtendHistory) {
    StockHistoryExtendRepository<T> repository = (StockHistoryExtendRepository<T>) stockExtendHistoryRepositoryFactory.getRepositoryExtendMap()
        .get(stockExtendHistory.getClass());
    if (repository != null) {
      repository.save(stockExtendHistory);
    } else {
      throw new RuntimeException(
          "No repository found for stockName: " + stockExtendHistory.getClass().getName());
    }
  }
  public <T extends StockHistory> void deleteAll(String stockName) {
    stockExtendHistoryRepositoryFactory.delAll(stockName);
  }
}