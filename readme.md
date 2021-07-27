# Builders Client API

## Detalhes do projeto

Aplicação responsável pela manipulação de clientes via API. 

Esse projeto possui:

- CRUD para manipulação de clientes seguindo os princípios e regras do REST;
- Validação da entrada de dados nas API's (*javax.validation.constraints*) para permitir que os dados da aplicação respeitem as constraints estabelecidas.
- Utilização de boas praticas de código (*Clean code*) e Design Patterns, como, Strategy, Chain of Responsability, Builder...
- Testes unitários na camada de serviço e nas validações do DTO usado pelo controlador para não permitir que o usuário conheça detalhes de implementação.
- Teste integrado nas API's criadas, validando os status code e o funcionamento das camadas de serviço e persistencia.
- Exception handler para tratamento de exceções.
- Disponilidade para que os testes nas API's sejam feitos localmente via docker ou então utilizando o serviço em cloud hospedado no heroku.
- Postman collection com testes já prontos para serem executados no ambiente local e cloud, arquivo está na raiz *Builders.postman_collection.json*

## Tecnologias utilizadas

- Java 1.8
- PostgreSQL
- PgAdmin4
- Maven
- Docker/Docker-Compose
- Lombok
- Spring Boot
- Spring Jpa
- Spring Test
- Spring Validation
- Spring Hateoas
- Spring Test

## Como Configurar:

Após checado o repositório devemos ir até a raiz do projeto, e então executar o seguinte comando:

```bash
docker-compose up -d
```

O Comando acima criará o ambiente contendo API, PostgresSQL e Pgadmin.
Agora está quase tudo pronto para que a aplicação seja testada, faltando apenas importar a collection disponibilizada no postman.

O PgAdmin permite que você manipule o banco de dados caso não tenha nenhuma outra ferramenta de administração. O mesmo pode ser acessado através do link http://localhost:16543/login

### Credenciais para acesso ao banco:

```bash
Host: localhost
Port: 15432
Login: postgres
Senha: PgAdmin2021!
```


