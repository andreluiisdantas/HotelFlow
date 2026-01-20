# 🏨 HotelFlow (Backend API)

Um **Sistema de Gerenciamento de Hotel (PMS)** focado no **backend**, construído com **Java** e **Spring Boot**.

Este repositório contém o código-fonte da API do **HotelFlow**. O objetivo principal deste projeto é servir como um **laboratório de estudos** para aprofundamento em arquitetura Spring, boas práticas de desenvolvimento backend, modelagem de dados e criação de **APIs RESTful robustas**.

> **Nota:** Este projeto é focado exclusivamente no **Back-end**. Não há interface gráfica (Frontend).  
> A interação ocorre via ferramentas de teste de API como **Postman** ou **Insomnia**.

---

## 🎯 Objetivo de Estudo

Embora simule um produto real para **pequenos e médios hotéis**, o foco é **técnico e educacional**. O projeto explora:

- **Spring Boot 3+ / 4**
  - Configuração
  - Injeção de Dependência
  - Ecossistema Spring
- **Spring Data JPA**
  - Mapeamento Objeto-Relacional (ORM)
  - Abstração de repositórios
- **Arquitetura em Camadas**
  - Controller
  - Service
  - Repository
  - Model
- **API REST**
  - Design de endpoints
  - Tratamento de erros
  - DTOs (Data Transfer Objects)
- **Regras de Negócio**
  - Reservas
  - Disponibilidade
  - Faturamento

---

## 🛠️ Stack Tecnológica

- **Linguagem:** Java 21  
- **Framework:** Spring Boot  
- **Banco de Dados:** PostgreSQL  
- **ORM:** Hibernate / Spring Data JPA  
- **Gerenciador de Dependências:** Maven  
- **Ferramentas:** Lombok, Spring DevTools  

---

## 🏛️ Domínios do Sistema

O sistema é organizado em pacotes que refletem as responsabilidades de negócio do hotel:

- **Users**
  - Gerenciamento de funcionários
  - Controle de acesso

- **Guests**
  - CRM do hotel
  - Perfil e histórico dos hóspedes

- **Property**
  - Inventário físico do hotel
  - *Entidades:*
    - Tipos de Quarto
    - Quartos
    - Status de Governança

- **Rates**
  - Motor de precificação
  - *Entidades:*
    - Planos de Tarifas
    - Preços por temporada

- **Reservations**
  - O coração do sistema
  - Orquestra disponibilidade e bloqueio de quartos

- **Folio**
  - Gestão financeira da estadia
  - Consumos, diárias e pagamentos

---

## 🚀 Começando (Setup e Instalação)

### 🧩 Pré-requisitos

- Java JDK 21
- Maven (ou `mvnw`)
- PostgreSQL em execução

---

### ⚙️ Configuração

#### 1️⃣ Clone o repositório

```bash
git clone https://github.com/seu-usuario/hotelflow.git
cd hotelflow
```

#### 2️⃣ Configure o Banco de Dados

Crie um banco de dados no PostgreSQL chamado **hotelflow**.

Arquivo: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotelflow
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

#### 3️⃣ Compile e baixe as dependências

```bash
mvn clean install
```

#### 4️⃣ Execute a aplicação

```bash
mvn spring-boot:run
```

A API estará disponível em:

```
http://localhost:8080
```

---

## 🧪 Testando a API

Como não há frontend, utilize ferramentas de teste de API:

- Postman
- Insomnia

---

## 🔖 Hashtags

```
#Java #SpringBoot #PostgreSQL #JPA #Backend #API #RestAPI
#HotelManagement #StudyProject
```
