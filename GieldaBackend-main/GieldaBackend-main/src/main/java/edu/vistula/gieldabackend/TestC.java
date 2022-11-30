package edu.vistula.gieldabackend;

import edu.vistula.gieldabackend.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("")
public class TestC {

    @Autowired
    DataTableRepository dataTableRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    AnalysisRepository analysisRepository;

    @GetMapping
    public ResponseEntity<Stock> test()
    {
        DataTable dataTable = new DataTable();
        dataTable.setName("Test Company");
        dataTable.setCode("123");
        dataTable.setEarliestNoting(new Date());
        Stock stock = new Stock();
        stock.setDataTable(dataTable);
        stock.setHigh(BigDecimal.valueOf(154.24));
        stock.setLow(BigDecimal.valueOf(102.43));
        stock.setAdjClose(BigDecimal.valueOf(99.99));
        stock.setOpen(BigDecimal.valueOf(242.50));
        stock.setDate(new Date());
        stock.setClose(BigDecimal.valueOf(123.24));
        Analysis analysis = new Analysis();
        analysis.setAvg(BigDecimal.valueOf(123));
        analysis.setMovingAvg(BigDecimal.valueOf(123));
        analysis.setDLine(BigDecimal.valueOf(123));
        analysis.setKLine(BigDecimal.valueOf(123));
        analysis.setRSI(BigDecimal.valueOf(123));
        dataTableRepository.save(dataTable);
        stockRepository.save(stock);
        analysis.setStock(stock);
        analysisRepository.save(analysis);

        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @GetMapping("/dataTables")
    public List<DataTable> getDataTables()
    {
        return dataTableRepository.findAll();
    }

    @GetMapping("/getCsv")
    public List<List<String>> getCsv()
    {
        Resource r = new ClassPathResource("GPW.WA.csv");
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(r.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
 }
