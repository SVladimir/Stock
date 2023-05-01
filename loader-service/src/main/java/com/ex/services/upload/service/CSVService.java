package com.ex.services.upload.service;


import com.ex.services.upload.exception.CsvParseException;
import com.ex.services.upload.factory.StockExtendHistoryFactory;
import com.ex.services.upload.helper.CSVHelper;
import com.ex.services.upload.model.extend.StockExtendHistory;
import com.ex.services.upload.model.stock.StockHistory;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
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
  private final StockExtendHistoryFactory stockExtendHistoryFactory;
  @Autowired
  CSVHelper csvHelper;

  public CSVService(
      StockHistoryService stockHistoryService,
      StockHistoryExtendService stockHistoryExtendService,
      StockExtendHistoryFactory stockExtendHistoryFactory) {
    this.stockHistoryService = stockHistoryService;
    this.stockHistoryExtendService = stockHistoryExtendService;
    this.stockExtendHistoryFactory = stockExtendHistoryFactory;
  }

  public void transferData(MultipartFile file) {
    try {
      LOGGER.info("start to store csv data: {}", file.getName());
      String stockName = CSVHelper.extractStockSymbol(file.getOriginalFilename());
      stockHistoryService.deleteAll(stockName);
      stockHistoryExtendService.deleteAll(stockName);
      List<StockHistory> listStockHistory = csvHelper.csvToStockHistory(stockName,
          file.getInputStream());
      List<StockExtendHistory> listExt = convert(listStockHistory);
      stockHistoryService.saveAll(stockName, listStockHistory);
      stockHistoryExtendService.saveAll(stockName,listExt);
      clearStockSummaryCache();

    } catch (CsvParseException e) {
      LOGGER.error("fail to parse csv file: {}", file.getName());
    } catch (IOException e) {
      LOGGER.info("fail to store csv data: {}", e.getMessage());
    }
  }

  @CacheEvict(value = {"stockSummary", "stockNormalize"}, allEntries = true)
  public void clearStockSummaryCache() {
    LOGGER.info("start clearStockSummaryCache");
    // This method will remove all entries from the 'stockSummary' cache when called.
  }
  private List<StockExtendHistory> convert(List<StockHistory> stockHistories)
  {
      return stockHistories.stream()
        .map(stockHistory -> {
          BigDecimal normalize = stockHistory.getHighPrice().subtract(stockHistory.getLowPrice()).divide(stockHistory.getLowPrice(), 4, RoundingMode.HALF_UP);
          StockExtendHistory stockExtendHistory=stockExtendHistoryFactory.createStockHistory(stockHistory.getClass() );
          stockExtendHistory.setDate(stockHistory.getDate());
          stockExtendHistory.setNormalize(normalize);
          return stockExtendHistory;
        })
        .collect(Collectors.toList());
  }


}
