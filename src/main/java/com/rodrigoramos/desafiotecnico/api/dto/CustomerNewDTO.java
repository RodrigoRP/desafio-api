package com.rodrigoramos.desafiotecnico.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerNewDTO {

    private String cnpj;
    private String name;
    private String businessArea;

    public CustomerNewDTO() {
    }

    public CustomerNewDTO(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

}
