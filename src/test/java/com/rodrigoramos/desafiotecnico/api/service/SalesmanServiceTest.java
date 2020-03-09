package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SalesmanServiceTest {

    @Mock
    private SalesmanRepository salesmanRepository;

    @InjectMocks
    private SalesmanServiceImpl salesmanService;

    @Test
    public void findsAnySalesmanById() {

        Salesman expected = new Salesman(null, "85424488000137", "João", 45000.00);
        when(salesmanRepository.findById(1L)).thenReturn(Optional.of((expected)));

        Salesman actual = salesmanService.find(1L);
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void throwsExceptionWhenInvalidSalesmanRequestedById() {
        when(salesmanRepository.findById(1L)).thenReturn(Optional.empty());
        salesmanService.find(1L);
    }

    @Test
    public void findsAllSalesman() {
        List<Salesman> salesmanList = new ArrayList<>();
        Salesman joao = new Salesman(null, "43125821061", "João", 45000.00);
        salesmanList.add(joao);
        when(salesmanRepository.findAll()).thenReturn(salesmanList);
        List<Salesman> actual = salesmanService.findAll();
        Assert.assertThat(actual, contains(hasProperty("name", Matchers.is("João"))));
    }

    @Test
    public void getNumberOfSalespeople() {

        List<Salesman> salesmanList = new ArrayList<>();
        Salesman salesman = new Salesman(null, "52358189030", "João", 12300.00);
        salesmanList.add(salesman);

        when(salesmanService.getNumberOfSalespeople()).thenReturn(1L);

    }

    @Test
    public void convertDtoToModel() {
        Salesman expected = new Salesman(null, "52358189030", "João", 12300.00);

        SalesmanNewDTO dto = new SalesmanNewDTO(expected.getCpf(), expected.getName(), expected.getSalary());

        assertThat(expected).isEqualToComparingFieldByField(salesmanService.convertToModel(dto));
    }

    @Test
    public void findByName() {
        Salesman expected = new Salesman(null, "85424488000137", "João", 45000.00);
        when(salesmanRepository.findByName(expected.getName())).thenReturn(expected);

        Salesman actual = salesmanService.findByName(expected.getName());
        assertThat(expected).isEqualToComparingFieldByField(actual);

    }



}
