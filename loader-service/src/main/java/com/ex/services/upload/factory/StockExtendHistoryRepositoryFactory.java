package com.ex.services.upload.factory;

import com.ex.services.upload.annotation.StockNameExtend;
import com.ex.services.upload.model.StockExtendHistory;
import com.ex.services.upload.repository.extend.StockHistoryExtendRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StockExtendHistoryRepositoryFactory {

  private final Map<String, StockHistoryExtendRepository<?>> repositoryMap;
  private final Map<Class, StockHistoryExtendRepository<?>> repositoryExtendMap;

  @Autowired
  public StockExtendHistoryRepositoryFactory(ApplicationContext applicationContext) {
    String[] beanNames = applicationContext.getBeanNamesForAnnotation(StockNameExtend.class);
    repositoryMap = Arrays.stream(beanNames)
        .map(applicationContext::getBean)
        .map(bean -> (StockHistoryExtendRepository<?>) bean)
        .collect(Collectors.toMap(
            repo -> Arrays.stream(repo.getClass().getInterfaces())
                .filter(iface -> iface.isAnnotationPresent(StockNameExtend.class))
                .findFirst()
                .map(iface -> iface.getAnnotation(StockNameExtend.class).value())
                .orElseThrow(() -> new IllegalStateException(
                    "StockNameExtend annotation not found on interfaces")),
            repo -> repo));
    repositoryExtendMap = new HashMap<>();

    Map<String, StockHistoryExtendRepository> beansOfType = applicationContext.getBeansOfType(StockHistoryExtendRepository.class);
    for (StockHistoryExtendRepository<?> repository : beansOfType.values()) {
      Class<? extends StockExtendHistory> entityClass = repository.getStockHistoryEntityClass();
      repositoryExtendMap.put(entityClass, repository);
    }
  }

  public <T extends StockExtendHistory> StockHistoryExtendRepository<T> getRepository(
      String stockName) {
    return (StockHistoryExtendRepository<T>) repositoryMap.getOrDefault(stockName, null);
  }

  public void delAll(String stockName) {
    repositoryMap.getOrDefault(stockName, null).deleteAll();
  }
}