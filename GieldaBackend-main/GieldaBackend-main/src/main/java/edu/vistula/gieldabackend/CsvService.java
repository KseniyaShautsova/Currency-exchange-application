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

    private static final int DATE_FIELD = 0;
    private static final int OPEN_FIELD = 1;
    private static final int HIGH_FIELD = 2;
    private static final int LOW_FIELD = 3;
    private static final int CLOSE_FIELD = 4;
    private static final int ADJ_CLOSE_FIELD = 5;
    private static final int VOLUME_FIELD = 6;

    public ChartResponse getDataFromCsv(String code) throws IOException {
        Resource resource = new ClassPathResource(code + ".csv");
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            List<Long> timestampList = new ArrayList<>();
            List<Float> openList = new ArrayList<>();
            List<Float> closeList = new ArrayList<>();
            List<Float> lowList = new ArrayList<>();
            List<Float> highList = new ArrayList<>();
            List<Float> adjCloseList = new ArrayList<>();
            List<Float> volumeList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("null")) continue;
                String[] fields = line.split(",");
                String dateString = fields[DATE_FIELD];
                if(!DateValidator.isValid(dateString)) continue;
                LocalDate date = createDateFromString(dateString);
                convertToTimestampAndAddToList(timestampList, date);
                addPriceToList(openList, fields[OPEN_FIELD]);
                addPriceToList(highList, fields[HIGH_FIELD]);
                addPriceToList(lowList, fields[LOW_FIELD]);
                addPriceToList(closeList, fields[CLOSE_FIELD]);
                addPriceToList(adjCloseList, fields[ADJ_CLOSE_FIELD]);
                addPriceToList(volumeList, fields[VOLUME_FIELD]);
            }
            return ChartResponse.builder()
                    .regularMarketPrice(closeList.get(closeList.size() - 1))
                    .regularMarketTime(timestampList.get(timestampList.size() - 1))
                    .timestamp(timestampList)
                    .close(closeList)
                    .open(openList)
                    .low(lowList)
                    .high(highList)
                    .adjClose(adjCloseList)
                    .volume(volumeList)
                    .build();
        }
    }


    private LocalDate createDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, formatter);
    }

    private void convertToTimestampAndAddToList(List<Long> timestampList, LocalDate date) {
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
