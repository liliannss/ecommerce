![](images/logo.jpg)
# E-COMMERCE DEVS-JAVAGIRL

### Contexto geral (problema)

- Sistema de e-commerce responsável pelo cadastro de vestuário, venda online e entrega.
_________________________________________________________________________________ 

### Qual tipo de vestuário?

https://pt.wikipedia.org/wiki/Categoria:Vestu%C3%A1rio

*Resposta: camisetas.*

- Sistema de e-commerce responsável pelo cadastro de camisetas, venda online e entrega.
_________________________________________________________________________________ 

### Qualquer pessoa pode comprar?

*Resposta: somente usuários cadastrados no sistema.*

- Sistema de e-commerce responsável pelo cadastro de camisetas, venda online para usuários cadastrados e entrega.
_________________________________________________________________________________ 

### Delimitação do Contexto

- Produto (camisetas)
- Venda
- Usuários
- Entrega

### Linguagem Onipresente (Ubíqua) - Exemplo

**Representação do Negócio**
- O sistema deverá suportar as operações de cadastro, consulta, atualização e remoção de camisetas
- Será possível realizar a consulta de camisetas por modelo
- Será possível realizar a consulta camisetas por valor
- Será possível realizar a consulta camisetas por cor
- Será possível realizar a consulta camisetas por tamanho

**Representação Técnica**
- CRUD (Create, Read, Update e Delete) de venda de camisetas
- Endpoint de consulta de camisetas por modelo
- Endpoint de consulta de camisetas por valor
- Endpoint de consulta de camisetas por cor
- Endpoint de consulta de camisetas por tamanho

**Requisitos Funcionais**

### Cadastro de Produto
- Para cadastro de um produto deverá obrigatoriamente ser informado:
    - Nome do modelo
    - Valor
    - Cores disponíveis
    - Modelagem:
      - Masculina Gola Olímpica
      - Feminina Gola U
      - Feminina Gola Olímpica
    - Tamanhos disponíveis:
      - P
      - M
      - G
      - GG
      - 2GG
      - 3GG
      - 4GG
    - Informações Sobre o produto (campo livre)
    - Total disponível no estoque

**Validações**
- Não será possível o cadastro de mais de um modelo com o mesmo nome
- O padrão monetário adotado será o REAL (duas casas decimais separada por vírgula)
- Não será possível o cadastro de mais de uma cor com o mesmo nome
- O total em estoque não deverá ultrapassar 100 unidades

**Critério de Aceite**
- O produto deverá ser inserido no Banco de Dados de acordo com as regras de validações

### Cadastro de Venda
- Para cadastro de uma venda obrigatoriamente deverá ser informado:
    - Código do cliente
    - Código do produto
    - Quantidade de unidades

**Validações**
- A quantidade vendida não poderá ultrapassar o total de 10 unidades por cliente

**Critério de Aceite**
- A venda deverá ser inserida no Banco de Dados de acordo com as regras de validações

**Bônus**
- Adicionar campo para avaliação das compras realizadas

### Cadastro de Usuário
- Para cadastro de um usuário deverá obrigatoriamente ser informado:
    - Nome Completo
    - Data de Nascimento
    - RG
    - CPF
    - Telefone fixo / celular
    - E-mail
    - Endereço

**Validações**
- Não será permitido o cadastro de usuário menor de 18 anos
- RG e CPF devem ser números válidos e únicos
- O E-mail informado deverá ser válido e único por usuário
- O CEP do endereço deverá ser informado

**Critério de Aceite**
- O usuário deverá ser inserido no Banco de Dados de acordo com as regras de validações

### Consulta de Usuário
- Consulta de usuário cadastrado por nome
- Consulta de usuário cadastrado por e-mail
- Consulta de usuário cadastrado por endereço

**Validações**
- Requisito Funcional Geral - RFG1

### Cadastro de Entrega
- Para cadastro de uma entrega deverá obrigatoriamente ser informado:
    - Código da venda

**Critério de Aceite**
- Deverá ser informado o valor do frete e prazo de entrega
- Requisito Funcional Geral - RFG1
- As consultas deverão ser paginadas de 15 em 15 registros, caso nenhum registro corresponda ao critério de busca deverá retornar uma lista vazia.

### Requisitos Não Funcionais

- Linguagem de Programação: Java 11
- Gerenciador de dependências: Maven
- Framework: Spring
- Banco de Dados: H2
- Documentação API: OpenAPI

### Evolução dos Requisitos Não Funcionais

- Banco de Dados: Postgres
- Spring Cloud
    - Ribbon
    - Resilience4J
    - Eureka
    - Zuul
    - Config Server
- Autenticação e Autorização
    - OAuth
    - JWT
    - Keycloak

### Disponibilização da Aplicação

- Docker
- Heroku
    - https://www.udemy.com/course/microsservicos-java-spring-cloud/
- Outros
    - Auditoria Banco de Dados
    - Normalização dos dados
    - Consulta com filtros utilizando Specification ou QueryDSL
    - Liquibase como controle de versão da Base de Dados

### CI/CD

- Travis CI ou GitHub Actions
- Sugestões de conteúdo / ferramentas:
    - https://medium.com/codigorefinado/code-review-revis%C3%A3o-de-c%C3%B3digo-pode-ser-automatizada-ba5f25882774
    - https://medium.com/thiagobarradas/an%C3%A1lise-cont%C3%ADnua-de-qualidade-do-software-bb7b03518bcc
    - https://imasters.com.br/cloud/spring-boot-na-nuvem-de-graca-e-com-as-melhores-praticas

### Cobertura de Código

- Ferramenta de Coverage
    - Codacy
    - Codecov

## Testes Unitários
- Mínimo de 80% de cobertura

## Observabilidade

- Prometheus
- Grafana
- Loki
- Jaeger
- ELK
- Sugestões de conteúdo / ferramentas:
    - https://github.com/liliannss/observabilidade


## Detalhes de Implementação

- Inicialização do projeto
    - https://start.spring.io/


- Estrutura de pacotes
    - Controllers -> Camada de Entrada /  Saída das requisições
    - Models -> Modelos de domínio
    - Services -> Classes de serviços
- Repositories -> Camada de manipulação de dados


- Visualizando ramo de dependências do projeto
    - mvn dependency:tree


- Banco de Dados
  - H2


- Configurações arquivo.properties:
    - https://howtodoinjava.com/spring-boot2/h2-database-example/


### Guide Projeto

**ResponseEntity**

**ResponseEntity como padrão de mercado**

**Retornando id do recurso criado no Location**
- https://github.com/algaworks/workshop-rest-spring/blob/master/Adicionando%20o%20recurso%20Autor%20%C3%A0%20nossa%20API/src/main/java/com/algaworks/socialbooks/resources/LivrosResources.java

**Logs**

**Enums**

**Classes Embedadas**

**DTO (Data Transfer Object)**

**Validações com Spring**

**Mapper**

**Lombok**

**ModelMapper**
- https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.4.4

**Swagger**
- http://localhost:8081/javagirls/swagger-ui/index.html

**Versionamento de API**
- /v1/users

**Context Path**
- server.servlet.context-path=/javagirls

**Exception Handler**

**Consultas**
- Query Methods
- JPQL
    - https://www.baeldung.com/spring-data-jpa-query
https://www.javaguides.net/2018/11/spring-data-jpa-creating-database-queries-using-query-annotation.html
- Query Nativa
    - http://www.h2database.com/html/functions.html#lower
- Consulta paginada

**Consumo de WebService**
- Feign
- WebClient
- RestTemplate
    - https://dev.to/daienelima/como-consumir-uma-api-na-sua-aplicacao-spring-boot-3p3a

**Variável de ambiente com Spring**

**Arquivo data.sql**
- A nomenclatura do arquivo deve ser data.sql

**Flyway**
- https://medium.com/cwi-software/versionar-sua-base-de-dados-com-spring-boot-e-flyway-be4081ddc7e5
- Boas Práticas:
    - V01__criar_e_registrar_categorias.sql
    - Iniciar com V maiúsculo;
    - Dois caracteres para versionamento (boa prática para manter a ordenação);
    - Separar por dois underscores (obrigatório);
    - Escrever o que a migração faz separando por um underscore.

**Comandos Maven**
- https://gist.github.com/erkobridee/3287943

**Readme**

**Pontos de Melhoria**

- Inclusão de Teste Unitário
- Utilizar versão mais atual do Spring com Spring Cloud para uso do Feign