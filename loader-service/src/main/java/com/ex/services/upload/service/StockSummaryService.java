package com.ex.services.upload.service;

import com.ex.services.upload.dto.StockSummaryDTO;
import com.ex.services.upload.factory.StockHistoryRepositoryFactory;
import com.ex.services.upload.model.stock.StockHistory;
import com.ex.services.upload.repository.stock.StockHistoryRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockSummaryService {

  private final StockHistoryRepositoryFactory repositoryFactory;

  @Cacheable("stockSummary")
  public List<StockSummaryDTO> getStockSummaryForMonth() {
    return repositoryFactory.getRepositoryMap().keySet().stream()
        .map(stockName -> {
          StockHistoryRepository<? extends StockHistory> repo = repositoryFactory.getRepository(
              stockName);
          List<? extends StockHistory> stockHistories = repo.findAll(
              Sort.by(Sort.Direction.DESC, "date"));

          var oldest = Optional.of(stockHistories.get(0).getOpenPrice())
              .orElse(BigDecimal.ZERO);
          var newest = Optional.of(
              stockHistories.get(stockHistories.size() - 1).getOpenPrice()).orElse(BigDecimal.ZERO);

          var min = stockHistories.stream()
              .map(StockHistory::getLowPrice)
              .min(Comparator.naturalOrder())
              .orElse(BigDecimal.valueOf(0));

          var max = stockHistories.stream()
              .map(StockHistory::getHighPrice)
              .max(Comparator.naturalOrder())
              .orElse(BigDecimal.valueOf(0));

          var normalize = BigDecimal.ZERO;
          if (min.compareTo(BigDecimal.ZERO) != 0) {
            normalize = max.subtract(min).divide(min, 4, RoundingMode.HALF_UP);
          }
          return new StockSummaryDTO(stockName, oldest, newest, min, max, normalize);
        })
        .toList();
  }

}
