package com.ex.services.upload.controller;

import com.ex.services.upload.dto.StockDataDTO;
import com.ex.services.upload.dto.StockNormalizeDTO;
import com.ex.services.upload.dto.StockSummaryDTO;
import com.ex.services.upload.helper.ConverterSumtoNorm;
import com.ex.services.upload.message.ResponseMessage;
import com.ex.services.upload.service.StockNormalizeService;
import com.ex.services.upload.service.StockSummaryService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

  private final StockSummaryService stockSummaryService;
  private final StockNormalizeService stockNormalizeService;

  @Autowired
  public StockController(StockSummaryService stockSummaryService,
      StockNormalizeService stockNormalizeService) {
    this.stockSummaryService = stockSummaryService;
    this.stockNormalizeService = stockNormalizeService;
  }


  @GetMapping("/summary")
  public List<StockSummaryDTO> getStockSummaryForMonth() {
    return stockSummaryService.getStockSummaryForMonth();
  }

  @GetMapping("/sortlist")
  public List<StockNormalizeDTO> getStockNormalize() {
    return ConverterSumtoNorm.convert(stockSummaryService.getStockSummaryForMonth());
  }

  @GetMapping("/{stockName}")
  public ResponseEntity<?> getStockSummury(@PathVariable("stockName") String stockName) {
    String message = "";
    try {
      StockSummaryDTO stockSummaryDTO = stockSummaryService.getStockSummaryForMonth().stream()
          .filter(stock -> stockName.equals(stock.getStockName())).findAny().stream().findAny()
          .get();
      StockDataDTO stockDataDTO = new StockDataDTO(stockSummaryDTO.getStockName(),
          stockSummaryDTO.getOldest(), stockSummaryDTO.getNewest(), stockSummaryDTO.getMin(),
          stockSummaryDTO.getMax());
      return ResponseEntity.ok(stockDataDTO);
    } catch (
        NoSuchElementException noSuchElementException) {
      message = "Could not find an information about stock: " + stockName + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseMessage(message));
    }

  }

  @GetMapping("/normalize")
  public ResponseEntity<?> getStockSummaryForMonth(@RequestParam("dateStr") String dateStr) {
    String message = "";
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      LocalDate date = LocalDate.parse(dateStr, formatter);
      return ResponseEntity.ok(stockNormalizeService.getNormalizeDate(date));
    } catch (
        NoSuchElementException noSuchElementException) {
      message = "Could not find an information about stock on date " + dateStr + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseMessage(message));
    }
  }
}