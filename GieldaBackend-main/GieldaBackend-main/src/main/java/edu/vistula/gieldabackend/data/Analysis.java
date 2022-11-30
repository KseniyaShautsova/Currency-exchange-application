package edu.vistula.gieldabackend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "analysis")
@Getter
@Setter
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "moving_avg")
    private BigDecimal movingAvg;
    @Column(name = "avg")
    private BigDecimal avg;
    @Column(name = "k_line")
    private BigDecimal kLine;
    @Column(name = "d_line")
    private BigDecimal dLine;
    @Column(name = "rsi")
    private BigDecimal RSI;
    @Column(name = "macd")
    private BigDecimal MACD;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    @JsonIgnore
    private Stock stock;

}
