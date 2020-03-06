package com.rodrigoramos.desafiotecnico.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SalesmanNewDTO {

    private String cpf;
    private String name;
    private BigDecimal salary;

    public SalesmanNewDTO() {
    }

    public SalesmanNewDTO(String cpf, String name, BigDecimal salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

}
