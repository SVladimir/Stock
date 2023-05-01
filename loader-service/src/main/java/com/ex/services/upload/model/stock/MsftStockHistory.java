package com.ex.services.upload.model.stock;

import com.ex.services.upload.annotation.StockName;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@StockName("MSFT")
@Table(name = "msft_stock_history")
public class MsftStockHistory extends StockHistory {

}
