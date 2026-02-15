# FilaEdu Backend

API REST do sistema FilaEdu, responsável por gerenciar filas, registros e estatísticas consumidas pelo frontend web e pela aplicação desktop.

## Descrição

Este projeto centraliza toda a lógica de negócio do FilaEdu, expondo endpoints REST para cadastro, consulta e análise dos dados da fila.

## Tecnologias

- Java 17
- Spring Boot
- Maven
- PostgreSQL
- JPA / Hibernate

## Pré-requisitos

Antes de rodar o projeto localmente, certifique-se de ter instalado:

- Java JDK 17
- Maven
- PostgreSQL
- Git

Verifique no terminal:

    java -version

## Clonando o repositório

    git clone https://github.com/Projeto-FilaEdu/filaedu-backend.git

## Configuração do banco de dados

Crie um banco de dados no PostgreSQL (exemplo: filaedu) e configure o arquivo:

    src/main/resources/application.properties

Exemplo de configuração:

    spring.datasource.url=jdbc:postgresql://localhost:5432/filaedu
    spring.datasource.username=postgres
    spring.datasource.password=senha

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

## Importando o projeto no Eclipse

1. Abra o Eclipse  
2. Vá em **File > Import**  
3. Selecione **Maven > Existing Maven Projects**  
4. Clique em **Next**  
5. Em **Root Directory**, selecione a pasta do projeto (`filaedu-backend`)  
6. Aguarde o Eclipse carregar o projeto  
7. Clique em **Finish**

## Executando a aplicação na IDE

No Eclipse:

1. Localize a classe principal anotada com `@SpringBootApplication`
2. Clique com o botão direito sobre a classe
3. Selecione **Run As > Java Application** ou **Run As > Spring Boot App**

A API estará disponível em:

    http://localhost:8080

## Integração

Este backend é consumido por:

- FilaEdu Frontend (Angular)
- FilaEdu Desktop (Java)
