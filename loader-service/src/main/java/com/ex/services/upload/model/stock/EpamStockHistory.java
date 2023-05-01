package com.ex.services.upload.model.stock;

import com.ex.services.upload.annotation.StockName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@StockName("EPAM")
@Table(name = "epam_stock_history")
public class EpamStockHistory extends StockHistory {
}
