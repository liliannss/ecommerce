# Projeto criado para fins de estudo

Este projeto, desenvolvido no decorrer de um grupo de estudos, tem como objetivo oferecer um desafio e abordar alguns
conceitos e tecnologias disponíveis que eventualmente irão auxiliar na implementação.

## E-Commerce 🚚

## Desafio Proposto 🕹

O desafio consiste, com base em um escopo definido parcialmente (incompleto), na criação de um sistema e-commerce que
deverá ser corrigido, caso haja necessidade, e evoluído a critério da pessoa que está implementando.

Também fica a critério a escolha das tecnologias e arquitetura que serão utilizadas.

As tecnologias apresentadas aqui estão direcionadas ao contexto **Java** e **Spring Framework**.

### Não estão sendo abordadas melhores práticas de desenvolvimento ou o aprofundamento das tecnologias sendo recomendado o estudo e prática dos tópicos abordados.

## Contextualização do Projeto 📚

_________________________________________________________________________________

- Sistema de e-commerce responsável pelo cadastro de vestuário, venda online e entrega.

### 🔎 Qual tipo de vestuário?

_________________________________________________________________________________

https://pt.wikipedia.org/wiki/Categoria:Vestu%C3%A1rio

*Resposta: camisetas.*

- Sistema de e-commerce responsável pelo cadastro de camisetas, venda online e entrega.

### 🔎 Qualquer pessoa pode comprar?

_________________________________________________________________________________

*Resposta: somente usuários cadastrados no sistema.*

- Sistema de e-commerce responsável pelo cadastro de camisetas, venda online para usuários cadastrados e entrega.

### 🔎 Delimitação do Contexto

_________________________________________________________________________________

- Produto (camisetas)
- Venda
- Usuários
- Entrega

### 🔎 Linguagem Onipresente x Técnico

_________________________________________________________________________________

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

## 📁 Requisitos Funcionais

_________________________________________________________________________________

### 💻 Cadastro de Produto

- Para cadastro de um produto deverá obrigatoriamente ser informado:
    - Nome do modelo
    - Valor
    - Cores disponíveis
    - Modelagem
        - Masculina Gola Olímpica
        - Feminina Gola U
        - Feminina Gola Olímpica
    - Tamanhos disponíveis
        - P
        - M
        - G
        - GG
        - 2GG
        - 3GG
        - 4GG
    - Informações sobre o produto (campo livre)
    - Total disponível em estoque

**Validações**

- Não será possível o cadastro de mais de um modelo com o mesmo nome
- O padrão monetário adotado será o REAL (duas casas decimais separadas por vírgula)
- Não será possível o cadastro de mais de uma cor com o mesmo nome
- O total em estoque não deverá ultrapassar 100 unidades por modelo

**Critério de Aceite**

- O produto deverá ser inserido no Banco de Dados de acordo com as regras de validações

### 💻 Cadastro de Venda

- Para cadastro de uma venda obrigatoriamente deverá ser informado:
    - Código do cliente
    - Código do produto
    - Quantidade de unidades a serem compradas

**Validações**

- A quantidade vendida não poderá ultrapassar o total de 10 unidades por cliente

**Critério de Aceite**

- A venda deverá ser inserida no Banco de Dados de acordo com as regras de validações

**Bônus**

- Adicionar campo livre para avaliação das compras realizadas por determinado cliente
- O campo deverá constar na listagem dos produtos

### 💻 Cadastro de Usuário

- Para cadastro de um usuário deverá obrigatoriamente ser informado:
    - Nome completo
    - Data de nascimento
    - RG
    - CPF
    - Telefone fixo
    - Telefone celular
    - E-mail
    - Endereço

**Validações**

- Não será permitido o cadastro de um usuário menor de 18 anos
- RG e CPF devem ser números válidos e únicos por usuário
- O E-mail informado deverá ser válido e único por usuário
- O CEP do endereço deverá ser informado obrigatoriamente

**Critério de Aceite**

- O usuário deverá ser inserido no Banco de Dados de acordo com as regras de validações

### 💻 Consulta de Usuário

- Consulta por nome
- Consulta por e-mail
- Consulta por endereço

**Validações**

- Requisito Funcional Geral - RFG1 (vide abaixo)

### 💻 Cadastro de Entrega

- Para cadastro de uma entrega deverá obrigatoriamente ser informado:
    - Código da venda

**Critério de Aceite**

- Deverá ser informado ao usuário o valor do frete e prazo de entrega
- A entrega deverá ser inserida no Banco de Dados

### 📁 Requisito Funcional Geral - RFG1

- As consultas deverão ser paginadas de 15 em 15 registros e caso nenhum registro corresponda ao critério de busca
  deverá ser retornada uma lista vazia

## 📁 Requisitos Não Funcionais

_________________________________________________________________________________

- Linguagem de Programação
    - Java 11
- Gerenciador de dependências
    - Maven
- Framework
    - Spring
- Banco de Dados
    - H2
- Documentação API
    - OpenAPI

## 💡 Evolução dos Requisitos Não Funcionais

_________________________________________________________________________________

Sugestão de evolução:

- Banco de Dados
    - Postgres
- Spring Cloud
    - Ribbon
    - Resilience4J
    - Eureka
    - Zuul
    - Config Server

_Referência_

- https://www.udemy.com/course/microsservicos-java-spring-cloud


- Autenticação e Autorização
    - OAuth
    - JWT
    - Keycloak

### Disponibilização da Aplicação

- Docker
- Heroku

### Evoluções Adicionais

- Auditoria Banco de Dados
- Normalização dos dados
- Consulta com filtros utilizando Specification ou QueryDSL
- Liquibase como controle de versão da Base de Dados

### CI/CD

- Travis CI ou GitHub Actions

_Referências complementares:_

- https://medium.com/codigorefinado/code-review-revis%C3%A3o-de-c%C3%B3digo-pode-ser-automatizada-ba5f25882774
- https://medium.com/thiagobarradas/an%C3%A1lise-cont%C3%ADnua-de-qualidade-do-software-bb7b03518bcc
- https://imasters.com.br/cloud/spring-boot-na-nuvem-de-graca-e-com-as-melhores-praticas

### Cobertura de Código

- Codecov

### Testes Unitários

- JUnit5

### Observabilidade

- Prometheus
- Grafana
- Loki
- Jaeger
- ELK

_Referência:_

- https://github.com/liliannss/observabilidade

## 💡 Guia Inicial de Implementação

_________________________________________________________________________________

- Inicialização do projeto
    - https://start.spring.io/


- Estrutura básica de pacotes
    - Controllers
        - Requisições (entrada / saída de dados)
    - Models
        - Modelos de domínio
    - Services
        - Classes de serviços
    - Repositories
        - Camada de manipulação de dados

## Fundamentos - Tecnologias - Ferramentas

Fundamentos, Tecnologias e Ferramentas básicos abordados no decorrer dos encontros e recomendados para estudo.
_________________________________________________________________________________

## 💾 Fundamentos

✅ **Java**

✅ **Programação Orientada a Objetos**

✅ **API**

✅ **Rest**

## 💾 Tecnologias

✅ **Spring Framework**

- Arquivo application.properties
- ResponseStatus x ResponseEntity
- Validações com Spring
- Anotações Jackson
- Variáveis com Spring

✅ **H2**

- http://localhost:8081/ecommerce/h2-console

_Referência_

- https://howtodoinjava.com/spring-boot2/h2-database-example/

✅ **Collections**

✅ **Logs**

✅ **Classe Embeddable**

✅ **DTO (Data Transfer Object)**

✅ **ModelMapper**

✅ **Lombok**

✅ **Swagger**

- http://localhost:8081/ecommerce/swagger-ui/index.html

✅ **Versionamento de API**

- /v1/users

✅ **Context Path**

- /ecommerce

✅ **Exception Handler**

✅ **Consultas**

- Query Methods
- JPQL
- Query Nativa
- Consulta paginada

_Referências_

- https://www.baeldung.com/spring-data-jpa-query
- https://www.javaguides.net/2018/11/spring-data-jpa-creating-database-queries-using-query-annotation.html
- http://www.h2database.com/html/functions.html#lower

✅ **Consumo de WebService**

- Feign
- WebClient
- RestTemplate

_Referência_

- https://dev.to/daienelima/como-consumir-uma-api-na-sua-aplicacao-spring-boot-3p3a

✅ **Arquivo data.sql**

- A nomenclatura do arquivo deve ser data.sql

✅ **Flyway**

- Boas Práticas:
    - V01__criar_e_registrar_categorias.sql
    - Iniciar com V maiúsculo;
    - Dois caracteres para versionamento (boa prática para manter a ordenação);
    - Separar por dois underscores (obrigatório);
    - Escrever o que a migração faz separando por um underscore.

_Referência_

- https://medium.com/cwi-software/versionar-sua-base-de-dados-com-spring-boot-e-flyway-be4081ddc7e5

✅ **Maven**

_Referência_

- https://gist.github.com/erkobridee/3287943

✅ **Readme**

✅ **Mocks**

_Referências_

- https://www.4devs.com.br/

## 💾 Ferramentas

✅ **IntelliJ**

✅ **Git**

✅ **GitHub**

✅ **cURL**

✅ **Postman**

## 🔨 Melhorias

_________________________________________________________________________________

Sugestão de melhorias:

- Inclusão de Testes Unitários
- Utilizar versão mais recente do Spring e corrigir possíveis erros de dependências
