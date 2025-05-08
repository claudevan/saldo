# Account Balance Service

## Descrição

Este projeto é um serviço RESTful para consulta de saldo de conta, desenvolvido em Java com o framework Spring.

## Funcionalidades

- Endpoint para consulta de saldo por ID de conta.
- Mock de API para simular os dados da conta.
- Logs e métricas para observabilidade.
- Tratamento de exceções e resiliência para múltiplas requisições simultâneas.

## Pré-requisitos

- Java 21
- Maven
- Docker e Docker Compose

## Configuração e Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/usuario/account-balance-service.git
   cd account-balance-service
   ```

2. Execute com Docker Compose:
   ```bash
   docker-compose up
   ```

3. Acesse a aplicação em `http://localhost:8080`.

## Testes

Execute os testes unitários com:
```bash
./mvnw test
```

## Observabilidade

- Métricas disponíveis no endpoint `/actuator/metrics`.

## Estrutura de Diretórios

- `controller`: Controladores REST.
- `service`: Lógica de negócios.
- `mock`: Simulação de API externa.
- `exception`: Tratamento de erros.