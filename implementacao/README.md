# Estoque Mateus API

Api desenvolvida em java + Spring utilizada para consulta de produtos e inserção de itens no estoque

## Endpoints
### Domínio Produto

**Get - /api/v1/product/id/{id}**
Busca um produto pelo id

Response:
```
{
  "id": 1,
  "name": "PRODUTO A",
  "description": "DESCRIÇÃO PRODUTO A",
  "price": 30,
  "barcode": "128971289",
  "createdAt": "2020-11-03T15:26:05",
  "updatedAt": "2020-11-03T15:26:05"
}
```

**Get - /api/v1/product/barcode/{barcode}**
Busca um produto pelo Código de Barras

Response:
```
{
  "id": 1,
  "name": "PRODUTO A",
  "description": "DESCRIÇÃO PRODUTO B",
  "price": 30,
  "barcode": "128971289",
  "createdAt": "2020-11-03T15:26:05",
  "updatedAt": "2020-11-03T15:26:05"
}
```
### Domínio Pedido de Estoque

**Get - /api/v1/product/description/{description}**
Busca um produto pela Descrição

Response:
```
{
  "id": 2,
  "name": "PRODUTO ALTERADO",
  "description": "DESCRIÇÃO PRODUTO B",
  "price": 25.5,
  "barcode": "837891891",
  "createdAt": "2020-11-03T15:26:05",
  "updatedAt": "2020-11-03T15:26:05"
}
```

**POST - /api/v1/stockOrder/in**
Cria um Pedido de Estoque de entrada
Request:
```
{
  "branchId": 1,
  "date": "2020-11-04T12:44:04.059Z",
  "items": [
    {
      "productId": 1,
      "quantity": 30
    }
  ],
  "providerId": 1,
  "userId": 1
}
```
Response:
```
201 - Created
```

**POST - /api/v1/stockOrder/out**
Cria um Pedido de Estoque de saída
Request:
```
{
  "branchId": 1,
  "customerId": 1,
  "date": "2020-11-04T12:48:22.512Z",
  "deliveryNote": "Entregar na rua ABC",
  "userId": 1
}
```
Response:
```
201 - Created
```

**POST - /api/v1/stockOrder/out/paymentMethod**
Seta uma forma de pagamento para o Pedido de Saida
Request:
```
{
  "paymentMethodId": 2,
  "stockOrderId": 1
}
```
Response:
```
200 - OK
```

**POST - /api/v1/stockOrder/out/paymentMethod**
Finaliza um Pedido de Estoque de saída
Request:
```
{
  "stockOrderId": 1
}
```
Response:
```
200 - OK
```

### Domínio Item Pedido de Estoque

**POST - /api/v1/stockOrderItem/out/add**
Adiciona um item a um pedido de estoque de saida
Request:
```
{
  "branchId": 1,
  "productId": 3,
  "quantity": 10,
  "stockOrderId": 1,
  "unitPrice": 30.0
}
```
Response:
```
201 - Created
```

**PUT - /api/v1/stockOrderItem/out/remove**
Remove um item de um pedido de estoque de saida
Request:
```
{
  "stockOrderItemId": 15
}
```
Response:
```
200 - OK
```

### Executando a aplicação

```bash
$ docker-compose up
```

## Melhorias
**Aplicar CDD nas classes de Serviços**<br />
**Refatorar as Validations**<br />
**Escrever os testes**<br />
**Fixar o link no Docker entre os containers**<br />
