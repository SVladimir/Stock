package com.ex.services.upload.factory;

import com.ex.services.upload.annotation.StockName;
import com.ex.services.upload.model.stock.StockHistory;
import com.ex.services.upload.repository.stock.StockHistoryRepository;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StockHistoryRepositoryFactory {

  private final Map<String, StockHistoryRepository<?>> repositoryMap;

  @Autowired
  public StockHistoryRepositoryFactory(ApplicationContext applicationContext) {
    String[] beanNames = applicationContext.getBeanNamesForAnnotation(StockName.class);
    repositoryMap = Arrays.stream(beanNames)
        .map(applicationContext::getBean)
        .map(bean -> (StockHistoryRepository<?>) bean)
        .collect(Collectors.toMap(
            repo -> Arrays.stream(repo.getClass().getInterfaces())
                .filter(iface -> iface.isAnnotationPresent(StockName.class))
                .findFirst()
                .map(iface -> iface.getAnnotation(StockName.class).value())
                .orElseThrow(() -> new IllegalStateException(
                    "StockName annotation not found on interfaces")),
            repo -> repo));
  }

  @SuppressWarnings("unchecked")
  public <T extends StockHistory> StockHistoryRepository<T> getRepository(String stockName) {
    return (StockHistoryRepository<T>) repositoryMap.getOrDefault(stockName, null);
  }
  public void delAll(String stockName){
     repositoryMap.getOrDefault(stockName, null).deleteAll();
  }
}