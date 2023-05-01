package com.ex.services.upload.service;

import com.ex.services.upload.factory.StockExtendHistoryRepositoryFactory;
import com.ex.services.upload.model.extend.StockExtendHistory;
import com.ex.services.upload.repository.extend.StockHistoryExtendRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockHistoryExtendService {

  private static final Logger LOGGER = LoggerFactory.getLogger(StockHistoryExtendService.class);
  private final StockExtendHistoryRepositoryFactory stockExtendHistoryRepositoryFactory;

  @Autowired
  public StockHistoryExtendService(
      StockExtendHistoryRepositoryFactory stockExtendHistoryRepositoryFactory) {
    this.stockExtendHistoryRepositoryFactory = stockExtendHistoryRepositoryFactory;
  }

  public <T extends StockExtendHistory> void saveAll(String stockName,
      List<T> stockExtendHistories) {
    StockHistoryExtendRepository<T> repository = stockExtendHistoryRepositoryFactory.getRepository(
        stockName);
    if (repository != null) {
      repository.saveAll(stockExtendHistories);
    } else {
      LOGGER.error("No repository found for stockName: {}", stockName);
    }
  }

  public void deleteAll(String stockName) {
    stockExtendHistoryRepositoryFactory.delAll(stockName);
  }
}