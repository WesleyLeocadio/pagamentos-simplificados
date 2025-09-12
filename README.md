# 💸 Carteira Digital API

API RESTful para simulação de transferências entre usuários, com regras de negócio específicas para tipos de usuários (comum e lojista).

## 🚀 Tecnologias

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- JUnit + Mockito
- Swagger

## 📦 Funcionalidades

- Cadastro de usuários com validação de CPF/CNPJ e e-mail únicos
- Transferência de valores entre usuários
- Validação de saldo
- Consulta a serviço externo de autorização
- Envio de notificação via serviço externo
- Transações reversíveis em caso de falha

## 🔁 Endpoint de Transferência

```http
POST /transfer
Content-Type: application/json

{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
