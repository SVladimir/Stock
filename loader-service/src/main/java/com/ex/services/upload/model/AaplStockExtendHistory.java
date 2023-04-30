package com.ex.services.upload.model;

import com.ex.services.upload.listener.StockEntityListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;

@Entity
@Table(name = "apple_stock_exctend_history")
public class AaplStockExtendHistory extends StockExtendHistory {
}
