package com.rodrigoramos.desafiotecnico.api.service.interfaces;

import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;

import java.util.List;

public interface SalesmanService {

    Salesman save(Salesman salesman);

    List<Salesman> findAll();

    Long getNumberOfSalespeople();

    Salesman findByName(Sale sale);
}
