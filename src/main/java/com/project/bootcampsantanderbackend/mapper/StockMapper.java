package com.project.bootcampsantanderbackend.mapper;

import com.project.bootcampsantanderbackend.model.Stock;
import com.project.bootcampsantanderbackend.model.dto.StockDTO;
import com.project.bootcampsantanderbackend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component //inject mapper
public class StockMapper {

    @Autowired //inject repository
    private StockRepository respository;

    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setDate(dto.getDate());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());

        return stock;
    }

    public StockDTO toDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setDate(stock.getDate());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());

        return dto;
    }

    public List<StockDTO> toDTO(List<Stock> listStock) {
        return listStock.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
