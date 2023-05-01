package com.ex.services.upload.service;

import com.ex.services.upload.exception.CsvParseException;
import com.ex.services.upload.helper.CSVHelper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CSVService.class);
  private final StockHistoryService stockHistoryService;
  private final StockHistoryExtendService stockHistoryExtendService;
  @Autowired
  CSVHelper csvHelper;

  public CSVService(
      StockHistoryService stockHistoryService,
      StockHistoryExtendService stockHistoryExtendService) {
    this.stockHistoryService = stockHistoryService;
    this.stockHistoryExtendService = stockHistoryExtendService;
  }

  public void save(MultipartFile file) {
    try {
      LOGGER.info("start to store csv data: {}", file.getName());
      String stockName = CSVHelper.extractStockSymbol(file.getOriginalFilename());
      stockHistoryService.deleteAll(stockName);
      stockHistoryExtendService.deleteAll(stockName);
      stockHistoryService.saveAll(stockName, csvHelper.csvToStockHistory(stockName,
          file.getInputStream()));
      clearStockSummaryCache();

    }catch (CsvParseException e) {
      LOGGER.error("fail to parse csv file: {}",file.getName());
    }
    catch (IOException e) {
      LOGGER.info("fail to store csv data: {}", e.getMessage());
    }
  }

  @CacheEvict(value = {"stockSummary", "stockNormalize"}, allEntries = true)
  public void clearStockSummaryCache() {
    LOGGER.info("start clearStockSummaryCache");
    // This method will remove all entries from the 'stockSummary' cache when called.
  }

}
