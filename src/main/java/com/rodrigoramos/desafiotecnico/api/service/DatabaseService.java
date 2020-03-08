package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.parser.CustomerParser;
import com.rodrigoramos.desafiotecnico.api.parser.SaleParser;
import com.rodrigoramos.desafiotecnico.api.parser.SalesmanParser;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import com.rodrigoramos.desafiotecnico.api.repository.SaleRepository;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.StringTokenizer;

@Service
public class DatabaseService {

    private static final String DELIMITER = "ç";
    private static final String CODE_SALESMAN = "001";
    private static final String CODE_CUSTOMER = "002";
    private static final String CODE_SALE = "003";

    private final SaleServiceImpl saleService;
    private final SalesmanRepository salesmanRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @Value("${data.caminho-entrada}")
    private String sourceFileStr;

    @Value("${data.caminho-saida}")
    private String targetFileStr;


    @Autowired
    public DatabaseService(SalesmanRepository salesmanRepository,
                           CustomerRepository customerRepository,
                           SaleRepository saleRepository,
                           SaleServiceImpl saleService) {
        this.salesmanRepository = salesmanRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.saleService = saleService;
    }

    public void instantiateDatabase(String fileName) {
        String sourceFileStrAux = sourceFileStr + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStrAux))) {
            String itemCsv = br.readLine();

            while (itemCsv != null && !itemCsv.isEmpty()) {
                StringTokenizer tokenizer = new StringTokenizer(itemCsv, DELIMITER);
                String token = tokenizer.nextToken();
                if (CODE_SALESMAN.equals(token)) {
                    salesmanRepository.save(SalesmanParser.parse(tokenizer));
                } else if (CODE_CUSTOMER.equals(token)) {
                    customerRepository.save(CustomerParser.parse(tokenizer));
                } else if (CODE_SALE.equals(token)) {
                    Sale sale = saleService.convertToModel(SaleParser.parse(tokenizer));
                    saleRepository.save(sale);
                }
                itemCsv = br.readLine();
            }
            //generateReport();
            logger.info("File uploaded successfully!!!");

        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
        }

    }

    private void generateReport() {
        String targetFileStrAux = targetFileStr + "report-" + LocalDate.now() + ".csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStrAux))) {
            long numberOfCustomers = customerRepository.count();
            long numberOfSalespeople = salesmanRepository.count();
            long idExpensiveSale = saleService.getIdMostExpensiveSale();
            String worstSalesman = saleService.getWorstSalesman();

            bw.write("Quantidade de clientes no arquivo de entrada: " + numberOfCustomers);
            bw.newLine();

            bw.write("Quantidade de vendedores no arquivo de entrada: " + numberOfSalespeople);
            bw.newLine();

            bw.write("ID da venda mais cara: " + idExpensiveSale);
            bw.newLine();

            bw.write("O pior vendedor: " + worstSalesman);
            bw.newLine();

            logger.info(targetFileStr, " CREATED!");

        } catch (IOException e) {
            logger.error("Error writing file: {}", e.getMessage());
        }
    }
}