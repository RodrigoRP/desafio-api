package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void savesCustomer() {
        Customer actual = new Customer(null, "85424488000137", "João", "Rural");

        Customer expected = new Customer(null, actual.getCnpj(), actual.getName(), actual.getBusinessArea());
        when(customerRepository.findById(1L)).thenReturn(Optional.of(expected));

        Customer entity = new Customer(null, actual.getCnpj(), actual.getName(), actual.getBusinessArea());
        when(customerRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Customer actual2 = customerService.save(actual);

        assertThat(actual2).isEqualToComparingFieldByField(expected);

    }

    @Test
    public void findsAnyCustomerById() {

        Customer expected = new Customer(null, "85424488000137", "João", "Rural");
        when(customerRepository.findById(1L)).thenReturn(Optional.of((expected)));

        Customer actual = customerService.find(1L);
        assertThat(expected).isEqualToComparingFieldByField(actual);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void throwsExceptionWhenInvalidCustomerRequestedById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        customerService.find(1L);
    }

    @Test
    public void findsAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer user = new Customer(null, "85424488000137", "João", "Rural");
        customerList.add(user);
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> actual = customerService.findAll();
        assertThat(actual, contains(hasProperty("name", Matchers.is("João"))));
    }

    @Test
    public void convertDtoToModel() {
        Customer expected = new Customer(null, "85424488000137", "João", "Rural");

        CustomerNewDTO dto = new CustomerNewDTO(expected.getCnpj(), expected.getName(), expected.getBusinessArea());

        assertThat(expected).isEqualToComparingFieldByField(customerService.convertToModel(dto));
    }

    @Test
    public void getsNumberOfCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer user = new Customer(null, "85424488000137", "João", "Rural");
        customerList.add(user);

        when(customerService.getNumberOfClients()).thenReturn(1L);
    }



}
