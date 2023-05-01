package com.ex.services.upload.service;

import com.ex.services.upload.helper.CSVHelper;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
@Autowired
CSVHelper csvHelper;
  private final StockHistoryService stockHistoryService;
  private final StockHistoryExtendService stockHistoryExtendService;

  public CSVService(
      StockHistoryService stockHistoryService, StockHistoryExtendService stockHistoryExtendService) {
      this.stockHistoryService = stockHistoryService;
    this.stockHistoryExtendService = stockHistoryExtendService;
  }

  public void save(MultipartFile file) {
    try {
      String stockName = CSVHelper.extractStockSymbol(file.getOriginalFilename());
      stockHistoryService.deleteAll(stockName);
      stockHistoryExtendService.deleteAll(stockName);
      stockHistoryService.saveAll(stockName,csvHelper.csvToStockHistory(stockName,
          file.getInputStream()));
      clearStockSummaryCache();
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }
  @CacheEvict(value = {"stockSummary", "stockNormalize"}, allEntries = true)
  public void clearStockSummaryCache() {
    // This method will remove all entries from the 'stockSummary' cache when called.
  }

}
