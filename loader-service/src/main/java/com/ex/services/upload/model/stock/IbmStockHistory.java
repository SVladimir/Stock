package com.ex.services.upload.model.stock;

import com.ex.services.upload.annotation.StockName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@StockName("IBM")
@Table(name = "ibm_stock_history")
public class IbmStockHistory extends StockHistory {
}
