package com.ex.services.upload.service;

import com.ex.services.upload.factory.StockExtendHistoryRepositoryFactory;
import com.ex.services.upload.model.extend.StockExtendHistory;
import com.ex.services.upload.model.stock.StockHistory;
import com.ex.services.upload.repository.extend.StockHistoryExtendRepository;
import jakarta.transaction.Transactional;
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
  @Transactional
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