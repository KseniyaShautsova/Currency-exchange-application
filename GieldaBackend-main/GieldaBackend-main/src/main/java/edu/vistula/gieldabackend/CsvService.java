package edu.vistula.gieldabackend;

import edu.vistula.gieldabackend.data.ChartResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private static final int DATE_HEADER = 0;
    private static final int OPEN_HEADER = 1;
    private static final int HIGH_HEADER = 2;
    private static final int LOW_HEADER = 3;
    private static final int CLOSE_HEADER = 4;

    public ChartResponse getDataFromCsv(String code) throws IOException {
        Resource resource = new ClassPathResource(code + ".csv");
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            List<Long> timestampList = new ArrayList<>();
            List<Float> openList = new ArrayList<>();
            List<Float> closeList = new ArrayList<>();
            List<Float> lowList = new ArrayList<>();
            List<Float> highList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String dateString = fields[DATE_HEADER];
                if(!DateValidator.isValid(dateString)) continue;
                LocalDate date = createDateFromString(dateString);
                addDateToTimestampList(timestampList, date);
                addPriceToList(openList, fields[OPEN_HEADER]);
                addPriceToList(highList, fields[HIGH_HEADER]);
                addPriceToList(lowList, fields[LOW_HEADER]);
                addPriceToList(closeList, fields[CLOSE_HEADER]);
            }
            return ChartResponse.builder()
                    .regularMarketPrice(closeList.get(closeList.size() - 1))
                    .regularMarketTime(timestampList.get(timestampList.size() - 1))
                    .timestamp(timestampList)
                    .close(closeList)
                    .open(openList)
                    .low(lowList)
                    .high(highList)
                    .build();
        }
    }

    private LocalDate createDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
    }

    private void addDateToTimestampList(List<Long> timestampList, LocalDate date) {
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Long timestamp = instant.toEpochMilli();
        timestampList.add(timestamp);
    }

    private void addPriceToList(List<Float> priceList, String price) {
        try {
            priceList.add(Float.valueOf(price));
        }
        catch (NumberFormatException e) {
            priceList.add(null);
        }
    }

}
