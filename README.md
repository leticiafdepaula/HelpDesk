# 🛠️ HelpDesk API

Bem-vindo à **HelpDesk API** — uma API RESTful desenvolvida com Java e Spring Boot para o gerenciamento de chamados de suporte técnico, com autenticação, controle de acesso, integração com banco de dados.

---

## 🚀 Tecnologias Utilizadas

- ✔️ Java 17+
- ✔️ Spring Boot
- ✔️ Spring Data JPA (Hibernate)
- ✔️ Spring Security
- ✔️ H2 Database (em memória para dev)
- ✔️ Maven
- ✔️ Swagger / OpenAPI 3

---

## 📌 Funcionalidades

- Cadastro de chamados com prioridade, status, técnico e cliente
- Autenticação básica (em memória)
- Validações automáticas com Bean Validation
- Integração com banco de dados H2
- Documentação da API com Swagger
- Tratamento de erros com mensagens personalizadas
- Filtros de segurança com suporte a JWT (em progresso)

---


### ✅ Pré-requisitos

- Java JDK 17 ou superior
- Maven

### ▶️ Executar com Maven Wrapper

## 📦 helpdesk
 
  ┣ 📂 domain             -> Entidades JPA (Chamado, Cliente, Técnico)
  
  ┣ 📂 dtos               -> Objetos de Transferência de Dados
 
 ┣ 📂 repositories       -> Interfaces de acesso ao banco (JPA)
 
 ┣ 📂 resources          -> Controllers REST
 
 ┣ 📂 services           -> Regras de negócio
 
 ┣ 📂 config             -> Configurações iniciais e perfil de teste
 
 ┗ 📂 exception          -> Tratamento de erros personalizados



        ## 🧾 Endpoints Principais

### 🔧 Chamados

| Método | Rota              | Descrição                         |
|--------|-------------------|-----------------------------------|
| GET    | `/chamados`       | Lista todos os chamados           |
| GET    | `/chamados/{id}`  | Retorna os detalhes de um chamado específico |
| POST   | `/chamados`       | Cria um novo chamado              |
| PUT    | `/chamados/{id}`  | Atualiza os dados de um chamado   |
| DELETE | `/chamados/{id}`  | Remove um chamado (se permitido)  |

---

### 👩‍🔧 Técnicos

| Método | Rota              | Descrição                          |
|--------|-------------------|------------------------------------|
| GET    | `/tecnicos`       | Lista todos os técnicos            |
| GET    | `/tecnicos/{id}`  | Detalha um técnico específico      |
| POST   | `/tecnicos`       | Cadastra um novo técnico           |
| PUT    | `/tecnicos/{id}`  | Atualiza dados de um técnico       |
| DELETE | `/tecnicos/{id}`  | Remove um técnico                  |

---

### 🧑 Clientes

| Método | Rota              | Descrição                          |
|--------|-------------------|------------------------------------|
| GET    | `/clientes`       | Lista todos os clientes            |
| GET    | `/clientes/{id}`  | Detalha um cliente específico      |
| POST   | `/clientes`       | Cadastra um novo cliente           |
| PUT    | `/clientes/{id}`  | Atualiza dados de um cliente       |
| DELETE | `/clientes/{id}`  | Remove um cliente                  |

---

### 🔐 Autenticação

| Método | Rota     | Descrição                          |
|--------|----------|------------------------------------|
| POST   | `/login` | Autentica o usuário e gera o token JWT |

> ⚠️ Para acessar os endpoints protegidos, é necessário enviar o token JWT no header da requisição:
                
Authorization: Bearer {seu_token_aqui}



