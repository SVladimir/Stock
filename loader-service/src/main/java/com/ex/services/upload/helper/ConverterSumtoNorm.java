package com.ex.services.upload.helper;

import com.ex.services.upload.dto.StockNormalizeDTO;
import com.ex.services.upload.dto.StockSummaryDTO;
import java.util.Comparator;
import java.util.List;

public class ConverterSumtoNorm {

  public static List<StockNormalizeDTO> convert(List<StockSummaryDTO> stockSummaryList) {
    return stockSummaryList.stream()
        .map(stockSummary -> {
          return new StockNormalizeDTO(stockSummary.stockName(), stockSummary.normalize());
        })
        .sorted(Comparator.comparing(StockNormalizeDTO::normalize).reversed())
        .toList();
  }


}
