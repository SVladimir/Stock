package com.ex.services.upload.model.stock;

import com.ex.services.upload.listener.StockEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Setter
@Getter
@Entity
@EntityListeners({StockEntityListener.class})
public abstract class StockHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
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