package com.rodrigoramos.desafiotecnico.api.controller;


import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"api/v1/sale"})
public class SaleController {

    private final SaleServiceImpl saleService;

    @Autowired
    public SaleController(SaleServiceImpl saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SaleNewDTO saleNewDTO) {
        Sale sale = saleService.convertToModel(saleNewDTO);
        sale = saleService.save(sale);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(sale.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        List<Sale> saleList = saleService.findAll();
        return ResponseEntity.ok().body(saleList);
    }

    @GetMapping(value = "/expensive")
    public Long getMostExpensiveSale() {
        return saleService.getIdMostExpensiveSale();
    }


}
