package com.ex.services.upload.model;

import com.ex.services.upload.listener.StockEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
@EntityListeners({StockEntityListener.class})
public abstract class StockHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private BigDecimal openPrice;

  @Column(nullable = false)
  private BigDecimal highPrice;

  @Column(nullable = false)
  private BigDecimal lowPrice;
  @Column(nullable = false)
  private BigDecimal closePrice;

  @Column(nullable = false)
  private Long volume;

}