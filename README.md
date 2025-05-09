# Account Balance Service

## Descrição

Este projeto é um serviço RESTful para consulta de saldo de conta, desenvolvido em Java com o framework Spring. O projeto também inclui uma API fake utilizando JSON Server para simular os dados das contas.

## Funcionalidades

- Endpoint para consulta de saldo por ID de conta.
- Mock de API para simular os dados das contas.
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

3. Acesse a aplicação em:
   - Serviço principal: `http://localhost:8080`
   - API Fake (JSON Server): `http://localhost:3000`

## Testes

Execute os testes unitários com:
```bash
./mvnw test
```

## Observabilidade

- Métricas disponíveis no endpoint `/actuator/metrics`.

---

## API Fake com JSON Server

A API fake roda no `http://localhost:3000` e simula os dados das contas. Abaixo estão as rotas disponíveis e exemplos de chamadas.

### Endpoints Disponíveis

1. **Listar todas as contas**
   ```http
   GET http://localhost:3000/contas
   ```

   **Exemplo de resposta:**
   ```json
   [
     {
       "idConta": "5df13be4-423e-42a2-8932-0dbf6acd3b29",
       "agencia": "6315",
       "conta": "0993149",
       "dac": "8",
       "idCliente": "10fa8077-2g27-44eb-8d12-7a3d6290ca35",
       "saldo": "300.00"
     },
     ...
   ]
   ```

2. **Consultar uma conta específica pelo ID**
   ```http
   GET http://localhost:3000/contas/{idConta}
   ```

   **Exemplo de chamada:**
   ```http
   GET http://localhost:3000/contas/5df13be4-423e-42a2-8932-0dbf6acd3b29
   ```

   **Exemplo de resposta:**
   ```json
   {
     "idConta": "5df13be4-423e-42a2-8932-0dbf6acd3b29",
     "agencia": "6315",
     "conta": "0993149",
     "dac": "8",
     "idCliente": "10fa8077-2g27-44eb-8d12-7a3d6290ca35",
     "saldo": "300.00"
   }
   ```

---

### Lista de IDs Disponíveis

Abaixo está a lista de IDs disponíveis para consulta na API fake (`http://localhost:3000/contas`):

| ID da Conta                              | Agência | Conta    | DAC | ID do Cliente                          | Saldo    |
|------------------------------------------|---------|----------|-----|----------------------------------------|----------|
| `5df13be4-423e-42a2-8932-0dbf6acd3b29`   | 6315    | 0993149  | 8   | `10fa8077-2g27-44eb-8d12-7a3d6290ca35` | `300.00` |
| `1f2a4bcd-7c9e-11ed-a1eb-0242ac120002`   | 1234    | 5678901  | 5   | `20fa8077-3c47-54cb-9d12-8b3f6291ab56` | `1500.75` |
| `2b3c4d5e-7c9e-11ed-a1eb-0242ac120003`   | 4321    | 1987654  | 3   | `30fa8077-4d57-64db-ad12-9c4f7292bc67` | `750.50` |
| `3c4d5e6f-7c9e-11ed-a1eb-0242ac120004`   | 5678    | 2345678  | 4   | `40fa8077-5e67-74eb-be12-ad5f8293cd78` | `200.00` |
| `4d5e6f7g-7c9e-11ed-a1eb-0242ac120005`   | 8765    | 8765432  | 7   | `50fa8077-6f77-84fb-ce12-be6f9294de89` | `1250.00` |
| `5e6f7g8h-7c9e-11ed-a1eb-0242ac120006`   | 3456    | 6543210  | 2   | `60fa8077-7g87-94gb-de12-cf7f0295ef90` | `600.00` |
| `6f7g8h9i-7c9e-11ed-a1eb-0242ac120007`   | 2345    | 5432109  | 9   | `70fa8077-8h97-a4hc-ee12-dg8f1296fg01` | `850.25` |
| `7g8h9i0j-7c9e-11ed-a1eb-0242ac120008`   | 9876    | 1098765  | 1   | `80fa8077-9i07-b4id-fe12-eh9f2297gh12` | `450.00` |
| `8h9i0j1k-7c9e-11ed-a1eb-0242ac120009`   | 6543    | 3210987  | 6   | `90fa8077-aj17-c4je-ge12-fi0f3298hi23` | `950.75` |
| `9i0j1k2l-7c9e-11ed-a1eb-0242ac120010`   | 7654    | 4321098  | 0   | `a0fa8077-bk27-d4kf-he12-gj1f4299ij34` | `3000.00` |

---

## Estrutura de Diretórios

- `controller`: Controladores REST.
- `service`: Lógica de negócios.
- `mock`: Simulação de API externa.
- `exception`: Tratamento de erros.
- `model`: Armazenar as classes de objetos

---

## Observações

- Certifique-se de que o Docker Compose está configurado corretamente.
- Para modificar os dados do JSON Server, edite o arquivo `contasMock.json` e reinicie o contêiner.