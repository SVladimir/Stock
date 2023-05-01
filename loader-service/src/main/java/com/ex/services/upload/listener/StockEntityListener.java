package com.ex.services.upload.listener;
import com.ex.services.upload.factory.StockExtendHistoryFactory;
import com.ex.services.upload.model.StockExtendHistory;
import com.ex.services.upload.model.StockHistory;
import com.ex.services.upload.service.StockHistoryExtendService;
import jakarta.persistence.PostPersist;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class StockEntityListener implements ApplicationContextAware {

  private static ApplicationContext applicationContext;
  private static StockHistoryExtendService stockHistoryExtendService;
  private static StockExtendHistoryFactory stockExtendHistoryFactory;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    StockEntityListener.applicationContext = applicationContext;
    StockEntityListener.stockHistoryExtendService = applicationContext.getBean(StockHistoryExtendService.class);
    StockEntityListener.stockExtendHistoryFactory = applicationContext.getBean(StockExtendHistoryFactory.class);

  }

  @PostPersist
  public void onPrePersist(StockHistory stockHistory) {
    BigDecimal normalize = stockHistory.getHighPrice().subtract(stockHistory.getLowPrice()).divide(stockHistory.getLowPrice(), 4, RoundingMode.HALF_UP);
    StockExtendHistory stockExtendHistory = stockExtendHistoryFactory.createStockHistory(stockHistory.getClass());
    stockExtendHistory.setDate(stockHistory.getDate());
    stockExtendHistory.setNormalize(normalize);
    stockHistoryExtendService.save(stockExtendHistory);
  }
}