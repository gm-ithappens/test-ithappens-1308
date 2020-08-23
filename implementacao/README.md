# Controler de estoque API
> Essa é uma API pública de gerenciamento de controle de estoque 

[![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)](https://github.com/tterb/atomic-design-ui/blob/master/LICENSEs)
[![Version](https://badge.fury.io/gh/tterb%2FHyde.svg)](https://github.com/wennersgc/rh-api)

## Tecnologias

- JDK 14
- Maven 3
- Flyway
- Spring Boot 2
- Modelmapper
- Docker

## Executano o projeto com Docker
Com o docker e o docker-composer instalando na maquina execute o seguinte comando:

```shell script
docker-compose build
```

quando finalizar o camando anterior execute:

```shell script
docker-compose up -d
```

## Compilação e execução manual

Entre na diretório raiz da API e execute o comando:

```shell script
mvn clean package
```

Em seguida entre na pasta target e execute:

```shell script
java -jar estoque-api.jar
```

## Conexão com o banco de dados

A API tentará se conectar em uma base de dados Mysql com as seguintes credenciais:

- usuário: root
- senha: admin 

## Documentação da API

Ao rodar a API sua documentação pode ser acessada clicando no link abaixo:

http://localhost:8080/swagger-ui.html
