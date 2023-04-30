package com.ex.services.upload.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockNormalizeDTO {

  private String stockName;
  private BigDecimal normalize;
}
