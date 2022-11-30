package edu.vistula.gieldabackend.data;


import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ReportResponse {

    String regularMarketPrice;
    String regularMarketTime;
    List<StockResponse> stocks;

}
