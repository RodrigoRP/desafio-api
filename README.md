# desafio-api

## Observações.

- Foi realizado a validação do campo cpf da classe Salesman, e cnpj da classe Customer.
- A documentacao do projeto foi realizado com o Swagger.
- Uma venda é salva apenas se o vendedor estiver cadastrado
- Ao gerar o projeto, automaticamente o banco H2 é populado com o arquivo que se encontra na pasta ...//in/ 
- Foram realizados testes na camada de servico.
- É possivel registrar novos dados a partir dos enpoints, o relatorio é gerado automaticamente apenas quando inserido
novos arquivos no diretorio padrao, caso seja inserido via endpoint foi criado um endpoint especifico para gerar novo relatorio.

As informacoes abaixo é possivel ser consultada tambem atraves dos endpoints.
• Quantidade de clientes no arquivo de entrada
• Quantidade de vendedores no arquivo de entrada
• ID da venda mais cara
• O pior vendedor
