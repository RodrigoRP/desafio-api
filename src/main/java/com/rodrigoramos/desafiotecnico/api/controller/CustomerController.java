package com.rodrigoramos.desafiotecnico.api.controller;

import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"api/v1/client"})
public class CustomerController {

    private final CustomerServiceImpl clientService;

    @Autowired
    public CustomerController(CustomerServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CustomerNewDTO customerNewDTO) {
        Customer customer = clientService.convertToModel(customerNewDTO);
        customer = clientService.save(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customerList = clientService.findAll();
        return ResponseEntity.ok().body(customerList);
    }

    @GetMapping(value = "/count")
    public Long getNumberOfClients() {
        return clientService.getNumberOfClients();
    }


}
