# Documentação da sua Solução

## Como instalar

Depois de entrar na pasta implementacao

### Localmente

```bash
$ make  # para ver o menu
$ make install  # instalar dependencias
$ make db  # seed e migrations
$ make test  # rodar testes unitarios
$ make s  # run server
```

### Docker
```bash
$ make deploy  # iniciar containers com todos os pre-requisitos
$ make down  # destroi todos os containers
```

## Aplicacao

API

```
http://localhost:8000
```

Banco de dados (Adminer)

```
http://localhost:8000/db
```

## Competência banco de dados

```sql
-- Escrever uma consulta que retorne todos os produtos com quantidade maior ou igual a 100
select * from produtos p
inner join estoques e on e.produto_id = p.id
where e.quantidade >= 100;

-- Escrever uma consulta que traga todos os produtos que têm estoque para a filial de código 60
select * from produtos p
inner join estoques e on e.produto_id = p.id
where e.filial_id >= 60;

-- Escrever consulta que liste todos os campos para o domínio PedidoEstoque e ItensPedido filtrando apenas o produto de código 7993
select * from pedidos p
inner join itens i on i.pedido_id = p.id
where i.produto_id = 7993;

-- Escrever uma consulta que liste os pedidos com suas respectivas formas de pagamento
select * from pedidos pd
inner join pagamentos pg on pg.id = pd.pagamento_id;

-- Escrever um consulta para sumarizar e bater os valores da capa do pedido com os valores dos ítens de pedido
select p.*, sum(i.total) pedidos p
inner join itens i on i.pedido_id = p.id
group by p.id;

-- Escrever uma consulta para sumarizar o total dos itens por pedido e que filtre apenas os pedidos no qual a soma total da quantidade de ítens de pedido seja maior que 10
select p.*, sum(i.total) pedidos p
inner join itens i on i.pedido_id = p.id
where sum(i.total) > 10
group by p.id;
```
