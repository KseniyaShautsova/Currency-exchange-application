package edu.vistula.gieldabackend.data;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder
public class ReportResponse {

    Float regularMarketPrice;
    Long regularMarketTime;
    List<Long> timestamp;
    List<Float> open;
    List<Float> high;
    List<Float> low;
    List<Float> close;

}
