# 📄 Product Requirements Document (PRD)

## 🧠 Visão Geral

O projeto **pagamentos-simplificados** tem como objetivo criar uma API RESTful para simulação de transferências financeiras entre usuários, com regras de negócio específicas para tipos de usuários (comum e lojista). O foco é o estudo de arquitetura, testes, boas práticas e integração com serviços externos.

---

## 🎯 Objetivo

Desenvolver uma aplicação backend capaz de:

- Realizar cadastro de usuários (comum e lojista)
- Permitir transferências de valores entre usuários
- Validar saldo antes da transferência
- Consultar serviço externo para autorização
- Enviar notificação após transferência

---

## 🧩 Escopo

### Funcionalidades principais

- [x] Cadastro de usuários com validação de CPF/CNPJ e e-mail únicos
- [x] Transferência de valores entre usuários
- [x] Validação de saldo antes da transferência
- [x] Consulta a serviço externo de autorização (`GET https://util.devi.tools/api/v2/authorize`)
- [x] Envio de notificação via serviço externo (`POST https://util.devi.tools/api/v1/notify`)
- [x] Transação reversível em caso de falha
- [x] Documentação da API com Swagger

### Fora do escopo

- Autenticação de usuários
- Interface frontend
- Cadastro via interface gráfica

---

## 🧪 Requisitos Técnicos

- **Linguagem:** Java 21
- **Framework:** Spring Boot
- **Banco de dados:** PostgreSQL
- **Testes:** JUnit + Mockito
- **Containerização:** Docker
- **Documentação:** Swagger/OpenAPI

---

## 🔁 Contrato de API

### Endpoint de Transferência

```http
POST /transfer
Content-Type: application/json

{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
```

---

## 📚 Observações

- O serviço de autorização externo deve retornar autorização explícita para a transação ocorrer.
- O serviço de notificação deve ser chamado apenas após transferência bem-sucedida.
- Em caso de falha em qualquer etapa, a transação deve ser revertida.
- O projeto deve seguir boas práticas de arquitetura, testes automatizados e documentação clara.
