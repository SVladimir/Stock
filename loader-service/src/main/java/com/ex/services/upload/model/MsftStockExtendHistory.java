package com.ex.services.upload.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "msft_stock_exctend_history")
public class MsftStockExtendHistory extends StockExtendHistory {
}
