package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SaleRepository;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import com.rodrigoramos.desafiotecnico.api.service.interfaces.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SalesmanRepository salesmanRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SalesmanRepository salesmanRepository,
                           SaleRepository saleRepository) {
        this.salesmanRepository = salesmanRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale convertToModel(SaleNewDTO saleNewDTO) {
        Salesman salesman = salesmanRepository.findByName(saleNewDTO.getSalesmanName());
        return new Sale(saleNewDTO.getSaleId(), saleNewDTO.getSaleItems(), salesman);
    }

    @Override
    public Long getIdMostExpensiveSale() {
        List<Sale> saleList = saleRepository.findAll();
        return saleList.stream().max(Comparator.comparing(Sale::getValue)).orElseThrow(RuntimeException::new).getId();
    }

    @Override
    public Sale getWorstSale() {
        List<Sale> saleList = saleRepository.findAll();
        return saleList.stream().min(Comparator.comparing(Sale::getValue)).orElseThrow(RuntimeException::new);
    }
}
