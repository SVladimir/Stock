package com.ex.services.upload.factory;

import com.ex.services.upload.annotation.StockName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@SpringBootTest
/*@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:test.properties")*/
public class StockHistoryRepositoryFactoryTest {
/*
  @Autowired
  private ApplicationContext applicationContext;

  @MockBean
  private StockHistoryRepositoryFactory stockHistoryRepositoryFactory;

  @BeforeEach
  public void setUp() {
    when(applicationContext.getBeanNamesForAnnotation(StockName.class))
        .thenReturn(new String[0]);
    stockHistoryRepositoryFactory = new StockHistoryRepositoryFactory(applicationContext);
  }

  @Test
  public void testGetRepository() {
    stockHistoryRepositoryFactory.hashCode();
  }*/

}