package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.DataIntegrityException;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import com.rodrigoramos.desafiotecnico.api.service.interfaces.SalesmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository salesmanRepository;

    @Autowired
    public SalesmanServiceImpl(SalesmanRepository salesmanRepository) {
        this.salesmanRepository = salesmanRepository;
    }

    @Override
    public Salesman save(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

    @Override
    public List<Salesman> findAll() {
        return salesmanRepository.findAll();
    }

    @Override
    public Long getNumberOfSalespeople() {
        return salesmanRepository.count();
    }

    @Override
    public Salesman findByName(Sale sale) {
        return salesmanRepository.findByName(sale.getSalesmanName().getName());
    }

    @Override
    public Salesman find(Long id) {
        Optional<Salesman> salesman = salesmanRepository.findById(id);
        return salesman.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found!"));
    }

    @Override
    public void delete(Long id) {
        find(id);
        try {
            salesmanRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Unable to delete!");
        }
    }


    public Salesman convertToModel(SalesmanNewDTO salesmanNewDTO) {
        return new Salesman(null, salesmanNewDTO.getCpf(), salesmanNewDTO.getName(), salesmanNewDTO.getSalary());
    }


}

