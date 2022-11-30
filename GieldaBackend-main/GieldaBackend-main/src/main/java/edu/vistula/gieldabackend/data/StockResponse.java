package edu.vistula.gieldabackend.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class StockResponse {

    String timestamp;
    String open;
    String high;
    String low;
    String close;

    public static StockResponse fromStock(Stock stock)
    {
        return StockResponse.builder()
                .timestamp(stock.getDate().toString())
                .open(stock.getOpen().toString())
                .high(stock.getHigh().toString())
                .low(stock.getLow().toString())
                .close(stock.getClose().toString())
                .build();
    }

}
