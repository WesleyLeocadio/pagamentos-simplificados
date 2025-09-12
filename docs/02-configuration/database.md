
# Banco de Dados

O projeto utiliza **PostgreSQL** como banco de dados principal em produção e desenvolvimento, conforme definido nos requisitos técnicos e boas práticas do projeto.

- **Banco principal:** PostgreSQL
- **Banco em memória para testes:** H2 (usado apenas em ambiente de testes automatizados)
- Estrutura baseada em usuários, contas e transações
- Scripts de criação e migração devem ser mantidos na pasta `/resources/db` (ou `/resources`)

## Boas práticas de modelagem

- Tabelas e colunas em `snake_case`
- Chaves primárias do tipo UUID
- Campos `created_at` e `updated_at` para auditoria
- Respeito a constraints e integridade referencial
- Índices para performance em campos de busca e relacionamento

## Exemplo de configuração (application.properties)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pagamentos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Para rodar localmente sem PostgreSQL, o H2 pode ser usado apenas para testes, conforme dependência no `pom.xml`.
