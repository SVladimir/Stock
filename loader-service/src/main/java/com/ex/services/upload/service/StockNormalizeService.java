package com.ex.services.upload.service;

import com.ex.services.upload.dto.StockNormalizeDTO;
import com.ex.services.upload.factory.StockExtendHistoryRepositoryFactory;
import com.ex.services.upload.model.StockExtendHistory;
import com.ex.services.upload.repository.StockHistoryExtendRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StockNormalizeService {

  private final StockExtendHistoryRepositoryFactory repositoryFactory;

  @Autowired
  public StockNormalizeService(StockExtendHistoryRepositoryFactory repositoryFactory) {
    this.repositoryFactory = repositoryFactory;
  }

  @Cacheable("stockNormalize")
  public StockNormalizeDTO getNormalizeDate(LocalDate date) {
    return repositoryFactory.getRepositoryMap().keySet().stream()
        .map(stockName -> {
              StockHistoryExtendRepository<? extends StockExtendHistory> repo = repositoryFactory.getRepository(
                  stockName);
              BigDecimal normalize = repo.findByDate(date).map(StockExtendHistory::getNormalize)
                  .orElse(BigDecimal.ZERO);
              return new StockNormalizeDTO(stockName, normalize);
            }
        ).sorted(Comparator.comparing(StockNormalizeDTO::getNormalize).reversed()).findFirst()
        .get();

  }
}
