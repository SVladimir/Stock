package com.ex.services.upload.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockSummaryDTO {

  private String stockName;
  private BigDecimal oldest;
  private BigDecimal newest;
  private BigDecimal min;
  private BigDecimal max;
  private BigDecimal normalize;
}
