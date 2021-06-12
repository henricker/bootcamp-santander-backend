package com.project.bootcampsantanderbackend.service;

import com.project.bootcampsantanderbackend.exceptions.BusinessException;
import com.project.bootcampsantanderbackend.exceptions.NotFoundException;
import com.project.bootcampsantanderbackend.mapper.StockMapper;
import com.project.bootcampsantanderbackend.model.Stock;
import com.project.bootcampsantanderbackend.model.dto.StockDTO;
import com.project.bootcampsantanderbackend.repository.StockRepository;
import com.project.bootcampsantanderbackend.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class StockService {

    @Autowired //Inject repository
    private StockRepository repository;

    @Autowired //inject mapper
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());

        if(optionalStock.isPresent())
            throw new BusinessException(MessageUtil.STOCK_ALREADY_EXISTS);

        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    @Transactional(readOnly = true)
    public List<StockDTO> getAllStocks() {
        return mapper.toDTO(repository.findAll());
    }

    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());

        if(optionalStock.isPresent())
            throw new BusinessException(MessageUtil.STOCK_ALREADY_EXISTS);


        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        Optional<Stock> optionalStock = repository.findById(id);

        if(!optionalStock.isPresent())
            throw new NotFoundException();

        return mapper.toDTO(optionalStock.get());
    }

    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findById(id);
        repository.deleteById(id);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        Optional<List<Stock>> stocksOptionals = repository.findByToday(LocalDate.now());

        if(!stocksOptionals.isPresent())
            throw new NotFoundException();

        return stocksOptionals.get().stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
