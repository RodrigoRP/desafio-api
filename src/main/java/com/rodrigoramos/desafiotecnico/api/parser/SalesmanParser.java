package com.rodrigoramos.desafiotecnico.api.parser;

import com.rodrigoramos.desafiotecnico.api.model.Salesman;

import java.math.BigDecimal;
import java.util.StringTokenizer;

public class SalesmanParser {

    public static Salesman parse(StringTokenizer tokenizer) {
        Salesman salesman = new Salesman();
        salesman.setCpf(tokenizer.nextToken());
        salesman.setName(tokenizer.nextToken());
        salesman.setSalary(new BigDecimal(tokenizer.nextToken()));

        return salesman;
    }

    private SalesmanParser() {
    }
}
