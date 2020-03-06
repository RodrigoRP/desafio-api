package com.rodrigoramos.desafiotecnico.api.controller;


import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.service.SaleServiceImpl;
import com.rodrigoramos.desafiotecnico.api.service.SalesmanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"api/v1/salesman"})
public class SalesmanController {

    private final SaleServiceImpl saleService;
    private final SalesmanServiceImpl salesmanService;

    @Autowired
    public SalesmanController(SaleServiceImpl saleService, SalesmanServiceImpl salesmanService) {
        this.saleService = saleService;
        this.salesmanService = salesmanService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SalesmanNewDTO salesmanNewDTO) {
        Salesman salesman = salesmanService.convertToModel(salesmanNewDTO);
        salesman = salesmanService.save(salesman);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(salesman.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Salesman>> findAll() {
        List<Salesman> salesmanList = salesmanService.findAll();
        return ResponseEntity.ok().body(salesmanList);
    }

    @GetMapping(value = "/count")
    public Long getNumberOfSalespeople() {
        return salesmanService.getNumberOfSalespeople();
    }


    @GetMapping(value = "/worst")
    public ResponseEntity<Salesman> getWorstSalesman() {
        Sale sale = saleService.getWorstSale();
        Salesman salesman = salesmanService.findByName(sale);
        return ResponseEntity.ok().body(salesman);
    }


}
