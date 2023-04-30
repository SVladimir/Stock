package com.ex.services.upload.helper;

import com.ex.services.upload.factory.StockHistoryFactory;
import com.ex.services.upload.model.StockHistory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVHelper {

  public static String TYPE = "text/csv";
  private final StockHistoryFactory stockHistoryFactory;

  public CSVHelper(StockHistoryFactory stockHistoryFactory) {
    this.stockHistoryFactory = stockHistoryFactory;
  }

  public static boolean hasCSVFormat(MultipartFile file) {

    return TYPE.equals(file.getContentType());
  }

  public static ByteArrayInputStream tutorialsToCSV(List<StockHistory> tutorials) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
     /* for (Tutorial tutorial : tutorials) {
        List<String> data = Arrays.asList(
            String.valueOf(tutorial.getId()),
            tutorial.getTitle(),
            tutorial.getDescription(),
            String.valueOf(tutorial.isPublished())
        );

        csvPrinter.printRecord(data);
      }*/

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

  public static String extractStockSymbol(String input) {
    Pattern pattern = Pattern.compile(".*_(.+?)\\..*");
    Matcher matcher = pattern.matcher(input);

    if (matcher.find()) {
      return matcher.group(1);
    }
    return null;
  }

  public <T extends StockHistory> List<T> csvToStockHistory(String stockName, InputStream is) {
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
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }
}
