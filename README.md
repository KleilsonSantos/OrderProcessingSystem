Sure, here is the updated `README.md` with the additional content:

```markdown
🚀 Order Processing System - Java Spring Boot 🛠️📦 (Em Construção 🚧)

📌 Descrição Inicial

O Order Processing System é um sistema desenvolvido em Java Spring Boot para gerenciar pedidos de clientes, aplicando boas práticas e tecnologias modernas.

📌 Principais funcionalidades:
```textplain
✅ Cadastro e processamento de pedidos
✅ Filtragem de pedidos acima de R$100
✅ Integração com PostgreSQL usando JPA/Hibernate
✅ API RESTful seguindo padrões clean architecture
```

📌 Configuração do Ambiente de Desenvolvimento

### Arquivo `docker-compose.yml`

O arquivo `docker-compose.yml` é utilizado para definir e gerenciar os serviços Docker necessários para o projeto. No nosso caso, estamos utilizando um contêiner PostgreSQL para o banco de dados.

```yaml
services:
  postgres:
    image: postgres:14
    container_name: postgres_order-processing-system
    ports:
      - '5432:5432'
    restart: always
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    volumes:
      - postgres-data-order-processing-system:/var/lib/postgresql/data
    networks:
      - order-processing-system_custom

volumes:
  postgres-data-order-processing-system:
    name: postgres-data-order-processing-system

networks:
  order-processing-system_custom:
    driver: bridge
```

### 📌 Arquivo `.env`

O arquivo `.env` é utilizado para definir variáveis de ambiente que serão utilizadas pelo Docker Compose. Essas variáveis são referenciadas no arquivo `docker-compose.yml`.

Exemplo de conteúdo do arquivo `.env`:

```ini
POSTGRES_DB=order_processing_db
POSTGRES_USER=order_user
POSTGRES_PASSWORD=securepassword
```

### 📌 Configuração do Maven

O projeto utiliza Maven para gerenciamento de dependências. O arquivo `pom.xml` inclui as seguintes dependências principais:

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
	<dependency>
		<groupId>org.postgresql</groupId>
		<artifactId>postgresql</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

### 📌 Configuração do Spring Boot

O arquivo `application.properties` contém as configurações do Spring Boot, incluindo detalhes do banco de dados e outras propriedades importantes:

```ini
# Application Details
spring.application.name=Order Processing System

# Server Configuration
server.port=8081

# Database Configuration
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/orderdb

# SQL Initialization
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:data.sql
spring.sql.init.schema-locations=classpath:schema.sql

# Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.open-in-view=false

# Validation Configuration
spring.mvc.static-path-pattern=/static/**
spring.mvc.throw-exception-if-no-handler-found=true

# Package Scanning
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.initialization-fail-timeout=5000
```

🚧 O projeto está em desenvolvimento! 🚧
Sugestões, contribuições e feedbacks são bem-vindos! 🙌
```