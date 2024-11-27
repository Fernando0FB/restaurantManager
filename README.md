
# Restaurant Manager

Este projeto é uma API de gerenciamento de reservas de mesa de restaurante desenvolvida em Java utilizando o framework Spring Boot e Maven como ferramenta de build. 
## Funcionalidades


- Gerenciamento de Reservas de Mesas:
    - Criar, atualizar e cancelar reservas.
    - Verificar a disponibilidade de mesas para datas e    horários específicos.
- Disponibilidade em Tempo Real:
    - Visualizar o status de ocupação das mesas em tempo real.
- Prevenir reservas duplicadas, garantindo verificações precisas de disponibilidade.
- Buscar registros usando qualquer informação disponível.
## Stack utilizada

- Java, Spring Boot, Maven

## Rodando o projeto

```bash
  git clone https://github.com/Fernando0FB/restaurantManager
```

O projeto está vinculado a um banco RDS hospedado na AWS, e seu paramentros estão todos configurados no `application.properties`.

Todas as informações do banco, levam em consideração variáveis de ambiente, sendo elas:
  - URL_BANCO_AWS
  - USUARIO_BANCO_AWS
  - SENHA_BANCO_AWS
(Caso você não tenha um banco RDS, você pode alterar algumas coisas, para rodar da maneira que você achar melhor)


## Autores

- [@Fernando0FB](https://www.github.com/Fernando0FB)


## Documentação da API

#### Mesas

```http
GET /api/tables - Retorna todas as mesas;
``` 
```http
GET /api/tables/{id} - Retorna uma mesa por ID;
``` 
```http
POST /api/tables - Cria uma nova mesa;
``` 
```http
PUT /api/tables/{id} - Atualiza uma mesa existente;
``` 
```http
DELETE /api/tables/{id} - Deleta uma mesa pelo ID
``` 

#### Usuários

```http
GET /api/users - Retorna todos os usuários;
``` 
```http
GET /api/users/{id} - Retorna um usuário por ID;
``` 
```http
POST /api/users - Cria um novo usuário;
``` 
```http
PUT /api/users/{id} - Atualiza um usuário existente;
``` 
```http
DELETE /api/users/{id} - Deleta um usuário pelo ID
``` 

#### Clientes

```http
GET /api/customers - Retorna todos os clientes;  
``` 
```http
GET /api/customers/{id} - Retorna um cliente por ID;  
``` 
```http
POST /api/customers - Cria um novo cliente;  
``` 
```http
PUT /api/customers/{id} - Atualiza um cliente existente;  
``` 
```http
DELETE /api/customers/{id} - Deleta um cliente pelo ID;  
``` 

#### Reservas

```http
GET /api/reservations - Retorna todas as reservas;  
``` 
```http
GET /api/reservations/{id} - Retorna uma reserva por ID;  
``` 
```http
POST /api/reservations - Cria uma nova reserva;  
``` 
```http
PUT /api/reservations/{id} - Atualiza uma reserva existente;  
``` 
```http
DELETE /api/reservations/{id} - Deleta uma reserva pelo ID;  
``` 
