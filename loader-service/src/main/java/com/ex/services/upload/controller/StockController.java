package com.ex.services.upload.controller;

import com.ex.services.upload.dto.StockDataDTO;
import com.ex.services.upload.dto.StockNormalizeDTO;
import com.ex.services.upload.dto.StockSummaryDTO;
import com.ex.services.upload.helper.ConverterSumToNorm;
import com.ex.services.upload.service.StockNormalizeService;
import com.ex.services.upload.service.StockSummaryService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

  private final StockSummaryService stockSummaryService;
  private final StockNormalizeService stockNormalizeService;


  @GetMapping("/summary")
  public List<StockSummaryDTO> getStockSummaryForMonth() {
    return stockSummaryService.getStockSummaryForMonth();
  }

  @GetMapping("/sortlist")
  public List<StockNormalizeDTO> getStockNormalize() {
    return ConverterSumToNorm.convert(stockSummaryService.getStockSummaryForMonth());
  }

  @GetMapping("/{stockName}")
  public StockDataDTO getStockSummary(@PathVariable("stockName") String stockName) {
    return stockSummaryService.getStockSummaryForMonth().stream()
        .filter(stock -> stockName.equals(stock.stockName())).findAny().stream().findAny()
        .map(stockSummaryDTO -> new StockDataDTO(stockSummaryDTO.stockName(),
            stockSummaryDTO.oldest(), stockSummaryDTO.newest(), stockSummaryDTO.min(),
            stockSummaryDTO.max())).orElseThrow(() -> new NoSuchElementException(
            "Could not find an information about stock: " + stockName + "!"));
  }

  @GetMapping("/normalize")
  public StockNormalizeDTO getStockSummaryForMonth(@RequestParam("dateStr") String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate date = LocalDate.parse(dateStr, formatter);
    return stockNormalizeService.getNormalizeDate(date)
        .orElseThrow(() -> new NoSuchElementException(
            "Could not find an information about stock on date " + dateStr + "!"));
  }
}