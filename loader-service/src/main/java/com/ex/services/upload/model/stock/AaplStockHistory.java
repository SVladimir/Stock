package com.ex.services.upload.model.stock;

import com.ex.services.upload.annotation.StockName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@StockName("AAPL")
@Table(name = "apple_stock_history")
public class AaplStockHistory extends StockHistory {
}
