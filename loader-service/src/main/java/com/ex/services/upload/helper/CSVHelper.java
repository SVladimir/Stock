package com.ex.services.upload.helper;

import com.ex.services.upload.exception.CsvParseException;
import com.ex.services.upload.factory.StockHistoryFactory;
import com.ex.services.upload.model.stock.StockHistory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(CSVHelper.class);
  private static final String TYPE = "text/csv";
  private final StockHistoryFactory stockHistoryFactory;

  public CSVHelper(StockHistoryFactory stockHistoryFactory) {
    this.stockHistoryFactory = stockHistoryFactory;
  }

  public static boolean hasCSVFormat(MultipartFile file) {

    return TYPE.equals(file.getContentType());
  }

  public static String extractStockSymbol(String input) {
    Pattern pattern = Pattern.compile(".*_(.+?)\\..*");
    Matcher matcher = pattern.matcher(input);

    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }

  public <T extends StockHistory> List<T> csvToStockHistory(String stockName, InputStream is)
      throws CsvParseException {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is,
        StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

      List<T> stockHistories = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      for (CSVRecord csvRecord : csvRecords) {
        StockHistory stockHistory = stockHistoryFactory.createStockHistory(stockName);
        LocalDate date = LocalDate.parse(csvRecord.get("date"), formatter);
        stockHistory.setDate(date);
        stockHistory.setOpenPrice(new BigDecimal(csvRecord.get("Open")));
        stockHistory.setHighPrice(new BigDecimal(csvRecord.get("High")));
        stockHistory.setLowPrice(new BigDecimal(csvRecord.get("Low")));
        stockHistory.setClosePrice(new BigDecimal(csvRecord.get("Close")));
        stockHistory.setVolume(Long.parseLong(csvRecord.get("Volume").replace(",", "")));
        stockHistories.add((T) stockHistory);
      }

      return stockHistories;
    } catch (IOException e) {
      LOGGER.error("fail to parse CSV records for {}", stockName);
      throw new CsvParseException("fail to parse CSV records for " + stockName);
    }
  }
}
