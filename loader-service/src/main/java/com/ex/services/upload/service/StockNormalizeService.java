package com.ex.services.upload.service;

import com.ex.services.upload.dto.StockNormalizeDTO;
import com.ex.services.upload.factory.StockExtendHistoryRepositoryFactory;
import com.ex.services.upload.model.extend.StockExtendHistory;
import com.ex.services.upload.repository.extend.StockHistoryExtendRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockNormalizeService {

  private final StockExtendHistoryRepositoryFactory repositoryFactory;

  @Cacheable("stockNormalize")
  public StockNormalizeDTO getNormalizeDate(LocalDate date) {
    return repositoryFactory.getRepositoryMap().keySet().stream()
        .map(stockName -> {
              StockHistoryExtendRepository<? extends StockExtendHistory> repo = repositoryFactory.getRepository(
                  stockName);
              Optional<? extends List<? extends StockExtendHistory>> byDate = repo.findByDate(date);
              BigDecimal normalize = BigDecimal.ZERO;
              if (byDate.isPresent()) {
                normalize = byDate.get().get(0).getNormalize();
              }

              return new StockNormalizeDTO(stockName, normalize);
            }
        ).max(Comparator.comparing(StockNormalizeDTO::getNormalize))
        .get();

  }
}
