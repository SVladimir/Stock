package com.ex.services.upload.model.extend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.TableGenerator;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class StockExtendHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "StockExtendHistory_Gen")
  @TableGenerator(
      name = "StockExtendHistory_Gen",
      table = "ID_GEN",
      pkColumnName = "GEN_NAME",
      valueColumnName = "GEN_VAL",
      pkColumnValue = "StockExtendHistory_Id",
      initialValue = 1,
      allocationSize = 1
  )
  private Long id;
  @Column(nullable = false)
  private LocalDate date;
  @Column(nullable = false)
  private BigDecimal normalize;
}
