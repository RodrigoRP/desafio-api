# desafio-api

## Observações.

- Foi realizado a validação do campo cpf da classe Salesman, e cnpj da classe Customer.
- A documentacao do projeto foi realizado com o Swagger.
- Uma venda é salva apenas se o vendedor estiver cadastrado
- Ao gerar o projeto, automaticamente o banco H2 é populado com o arquivo que se encontra na pasta ...//in/ 
- Foram realizados testes na camada de servico.
- Os caminhos estao no arquivo de propriedades //resource//application-test.properties
- É possivel registrar novos dados a partir dos enpoints, o relatorio é gerado automaticamente apenas quando inserido
novos arquivos no diretorio padrao, caso seja inserido via endpoint foi criado um endpoint especifico para gerar novo relatorio.

As informacoes abaixo é possivel ser consultada tambem atraves dos endpoints.
• Quantidade de clientes no arquivo de entrada
• Quantidade de vendedores no arquivo de entrada
• ID da venda mais cara
• O pior vendedor


## Foram realizados os seguintes ajustes.

- Anteriormente precisava informar o nome do arquivo para fazer a inserção dos dados,
agora é realizado uma varredura na pasta \data\in\ , se conter arquivos no formato '.dat' .
é realizado a insercao dos dados, e consequentemente o relatorio é gerado no formato '.done.dat'
- O relatorio agora é gerado automaticamente, nao ha mais a necessidade de fazer uma chamada HTTP.
- Foi realizado o ajuste no caminho \data\in e \data\out, anteriormente o caminho estava fixo.
- Havia falhas nos testes, foi realizado o ajuste , nao estava considerando as informacoes que havia no banco.
- Somente é realizado a leitura de arquivos '.data', ao inicializar o sistema e após.
