package com.ex.services.upload.helper;

import com.ex.services.upload.dto.StockNormalizeDTO;
import com.ex.services.upload.dto.StockSummaryDTO;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterSumtoNorm {

  public static List<StockNormalizeDTO> convert(List<StockSummaryDTO> stockSummaryList) {
    return stockSummaryList.stream()
        .map(stockSummary -> {
          return new StockNormalizeDTO(stockSummary.getStockName(), stockSummary.getNormalize());
        })
        .sorted(Comparator.comparing(StockNormalizeDTO::getNormalize).reversed())
        .collect(Collectors.toList());
  }


}
