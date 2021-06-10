package com.project.bootcampsantanderbackend.controller;

import com.project.bootcampsantanderbackend.model.dto.StockDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/stock")
public class StockController {


    //consumes => What type of data i'll receive from body request ?
    //produces => What type of data in response i'll send to client
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto) {
        return ResponseEntity.ok(dto);
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll() {
        List<StockDTO> list = new ArrayList<>();

        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(100D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());

        StockDTO stock2 = new StockDTO();
        stock2.setId(1L);
        stock2.setName("Coca cola");
        stock2.setPrice(100D);
        stock2.setVariation(10D);
        stock2.setDate(LocalDate.now());

        list.add(stock1);
        list.add(stock2);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findOne(@PathVariable Long id) {

        List<StockDTO> list = new ArrayList<>();

        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(100D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());

        StockDTO stock2 = new StockDTO();
        stock2.setId(2L);
        stock2.setName("Coca cola");
        stock2.setPrice(100D);
        stock2.setVariation(10D);
        stock2.setDate(LocalDate.now());

        list.add(stock1);
        list.add(stock2);

        return ResponseEntity.ok(
                list.stream()
                .filter( stock ->
                         stock.getId().compareTo(id) == 0)
                .findFirst()
                .get());

    }

}
