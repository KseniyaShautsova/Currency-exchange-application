package edu.vistula.gieldabackend;

import edu.vistula.gieldabackend.data.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.*;

@RestController
@RequestMapping("/csv")
@AllArgsConstructor
public class CsvController {

    @Autowired
    private final CsvService csvService;

    @GetMapping("/{code}")
    public ResponseEntity<ChartResponse> getDataFromCsv(@PathVariable String code) throws IOException {
        ChartResponse chartResponse = csvService.getDataFromCsv(code);
        return ResponseEntity.ok(chartResponse);
    }
 }
