package com.ex.services.upload.model.stock;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.listener.StockEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@StockName("EPAM")
@Table(name = "epam_stock_history")
@EntityListeners({StockEntityListener.class})
public class EpamStockHistory extends StockHistory {
}
