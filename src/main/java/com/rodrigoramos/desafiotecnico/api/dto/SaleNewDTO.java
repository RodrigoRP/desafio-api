package com.rodrigoramos.desafiotecnico.api.dto;

import com.rodrigoramos.desafiotecnico.api.model.SaleItem;
import com.rodrigoramos.desafiotecnico.api.service.validation.SaleInsert;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SaleInsert
public class SaleNewDTO {

    @NotNull(message = "Required Field")
    private Long saleId;

    @NotEmpty(message = "Required Field")
    private List<SaleItem> saleItems = new ArrayList<>();

    @NotNull(message = "Name cannot be null")
    private String salesmanName;

    public SaleNewDTO() {
    }

    public SaleNewDTO(Long saleId, List<SaleItem> saleItems, String salesmanName) {
        this.saleId = saleId;
        this.saleItems = saleItems;
        this.salesmanName = salesmanName;
    }

}
