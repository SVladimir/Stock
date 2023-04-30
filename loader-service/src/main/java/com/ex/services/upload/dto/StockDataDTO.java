package com.ex.services.upload.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockDataDTO {

  private String stockName;
  private BigDecimal oldest;
  private BigDecimal newest;
  private BigDecimal min;
  private BigDecimal max;
}
