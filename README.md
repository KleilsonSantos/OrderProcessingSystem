## 🚀 Order Processing System - Java Spring Boot 🛠️📦 (Em Construção 🚧)

📌 Descrição Inicial

O Order Processing System é um sistema desenvolvido em Java Spring Boot para gerenciar pedidos de clientes, aplicando boas práticas e tecnologias modernas.

📌 Principais funcionalidades:
```textplain
✅ Cadastro e processamento de pedidos
✅ Filtragem de pedidos acima de R$100
✅ Integração com PostgreSQL usando JPA/Hibernate
✅ API RESTful seguindo padrões clean architecture
```
- Create new orders 🆕
- Retrieve all orders 📋
- Retrieve a specific order by ID 🔍
- Update existing orders ✏️
- Delete orders 🗑️


### 📌 Technologies Used 🛠️
- Java 17 ☕
- Spring Boot 3.4.4 🌱
- PostgreSQL 🐘
- Maven 🧩
- Docker 🐳
- Docker Compose 📦
- SonarQube 📊
- JUnit 5 🧪
- Mockito 🦠


### Prerequisites 📋
- Java 17 or higher
- Maven
- PostgreSQL

## Getting Started 🚀

### Installation 🛠️
1. Clone the repository:
```sh
git clone https://github.com/KleilsonSantos/order-processing-system.git
cd order-processing-system
```

### 📌 Configuração do Ambiente de Desenvolvimento

#### Arquivo `docker-compose.yml`

O arquivo `docker-compose.yml` é utilizado para definir e gerenciar os serviços Docker necessários para o projeto. No nosso caso, estamos utilizando um contêiner PostgreSQL para o banco de dados.

```yaml
services:

  postgres:
    image: postgres:14
    container_name: postgres_order-system
    ports:
      - '5432:5432'
    restart: always
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    volumes:
      - postgres-data_order-system:/var/lib/postgresql/data
    networks:
      - order_system_custom

  sonarqube:
    image: sonarqube:lts
    container_name: sonarqube_order-system
    restart: always
    depends_on:
      - postgres
    ports:
      - '9000:9000'
    environment:
      SONAR_JDBC_URL: jdbc:postgresql://postgres:5432/orderdb
      SONAR_JDBC_USERNAME: ${SONAR_JDBC_USERNAME}
      SONAR_JDBC_PASSWORD: ${SONAR_JDBC_PASSWORD}
      SONAR_TOKEN: ${SONAR_TOKEN}
    volumes:
      - sonarqube-conf_order-system:/opt/sonarqube/conf
      - sonarqube-data_order-system:/opt/sonarqube/data
      - sonarqube-logs_order-system:/opt/sonarqube/logs
      - sonarqube-extensions_order-system:/opt/sonarqube/extensions
      - sonarqube-bundled_order-system:/opt/sonarqube/lib/bundled-plugins
    networks:
      - order_system_custom

volumes:
  postgres-data_order-system:
    name: postgres-data_order-system
  sonarqube-data_order-system:
    name: sonarqube-data_order-system
  sonarqube-logs_order-system:
    name: sonarqube-logs_order-system
  sonarqube-extensions_order-system:
    name: sonarqube-extensions_order-system
  sonarqube-bundled_order-system:
    name: sonarqube-bundled_order-system
  sonarqube-conf_order-system:
    name: sonarqube-conf_order-system

networks:
  order_system_custom:
    name: order_system_custom
    driver: bridge
```

### 📌 Arquivo `.env`

Arquivo .env
O arquivo .env é utilizado para definir variáveis de ambiente que serão utilizadas pelo Docker Compose. Essas variáveis são referenciadas no arquivo docker-compose.yml. Isso permite uma configuração mais flexível e segura, pois as credenciais e outras informações sensíveis não são hardcoded no arquivo de configuração.

Exemplo de conteúdo do arquivo `.env`:

```ini
# Environment Variables for Order Service

# Configuration Port
SERVER_PORT=8081

# PostgreSQL Configuration
POSTGRES_DB=orderdb
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# PostgreSQL JDBC Driver
DATASOURCE_DRIVER=org.postgresql.Driver
DATASOURCE_URL=jdbc:postgresql://localhost:5432/orderdb

# SonarQube Configuration
SONAR_JDBC_USERNAME=postgres
SONAR_JDBC_PASSWORD=postgres
SONAR_TOKEN=seu_token
```

### 📌 Arquivo import.sql
O arquivo import.sql é utilizado para inicializar o banco de dados com dados padrão ou de teste. Ele é executado automaticamente pelo Spring Boot durante a inicialização da aplicação, garantindo que o banco de dados esteja pronto para uso imediato.

Exemplo de conteúdo do arquivo import.sql:
```sql
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,             -- Define o campo id como auto-incremento
    customer VARCHAR(255) NOT NULL,    -- Nome do cliente
    total DOUBLE PRECISION NOT NULL    -- Valor total
);

INSERT INTO orders (customer, total) VALUES ('Alice Martins', 120.75);
INSERT INTO orders (customer, total) VALUES ('Carlos Souza', 210.50);
INSERT INTO orders (customer, total) VALUES ('Bruna Silva', 85.40);
INSERT INTO orders (customer, total) VALUES ('Diego Fernandes', 150.00);
INSERT INTO orders (customer, total) VALUES ('Fabiana Lima', 99.99);
INSERT INTO orders (customer, total) VALUES ('Guilherme Santos', 180.49);
INSERT INTO orders (customer, total) VALUES ('Helena Costa', 55.99);
INSERT INTO orders (customer, total) VALUES ('Igor Almeida', 75.20);
INSERT INTO orders (customer, total) VALUES ('Juliana Pereira', 99.49);

```

### 📌 Classes de Exceção Personalizada
As classes de exceção personalizada são utilizadas para tratar erros específicos de forma mais clara e organizada. Isso melhora a manutenção do código e facilita o tratamento de erros.

Exemplo de uma classe de exceção personalizada:

```java
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
```

### 📌 Classe de Tratamento Global de Exceções
A classe de tratamento global de exceções é utilizada para capturar e tratar exceções de forma centralizada, melhorando a consistência e a clareza das respostas de erro da API.

Exemplo de uma classe de tratamento global de exceções:
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```


### 📌 Configuração do Maven

O projeto utiliza Maven para gerenciamento de dependências. O arquivo `pom.xml` inclui as seguintes dependências principais:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.4</version>
        <relativePath/>
    </parent>
    <groupId>com.example.order-processing-system</groupId>
    <artifactId>order-processing-system</artifactId>
    <version>0.1.2-SNAPSHOT</version>
    <name>Order Processing System</name>
    <description>Demo project for Spring Boot - Order Processing System</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>17</java.version>
        <sonar.host.url>http://localhost:9000</sonar.host.url>
    </properties>
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
        <dependency>
            <groupId>io.github.cdimascio</groupId>
            <artifactId>dotenv-java</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.38</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.13.0</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.18.0</version>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>versions-maven-plugin</artifactId>
                <version>2.15.0</version>
            </plugin>
            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.10</version>
                <executions>
                    <execution>
                        <id>prepare-agent</id>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>3.9.1.2184</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### 📌 Configuração do Spring Boot

O arquivo `application.properties` contém as configurações do Spring Boot, incluindo detalhes do banco de dados e outras propriedades importantes:

```textplain
# Application Details
spring.application.name=Order Processing System

# Server Configuration
server.port=8081

# Database Configuration
spring.datasource.jpa.show-sql=true
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/orderdb

# SQL Initialization
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=update

# Enable SQL script initialization
spring.sql.init.data-locations=classpath:import.sql

# Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.open-in-view=false

# Validation Configuration
spring.mvc.static-path-pattern=/static/**
spring.mvc.throw-exception-if-no-handler-found=true

# Logging Configuration
logging.level.org.springframework=DEBUG
logging.level.org.springframework.jdbc.datasource.init=DEBUG
logging.level.org.hibernate=DEBUG


# Package Scanning
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.auto-commit=true
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.pool-name=HikariPool-1
spring.datasource.hikari.max-lifetime=1800000
spring.datasource.hikari.connection-timeout=30000
```

###  📌 Build the project:
```
mvn clean install
```

###  📌 Run the application:
```
mvn spring-boot:run
```

### 📌 Run Docker Compose:
```sh
docker-compose up -d
```
### 📌 Access the Application
Acesse a aplicação em: [http://localhost:8081](http://localhost:8081)

### 📌 Access the SonarQube Dashboard
Acesse o SonarQube em: [http://localhost:9000](http://localhost:9000)

### 📌 Run the SonarQube Scanner
```sh
mvn clean verify sonar:sonar -Dsonar.scm.provider=git -Dsonar.projectKey=key_project -Dsonar.host.url=http://localhost:9000 -Dsonar.login=seu_token
```

### 📌 Run the JaCoCo Report
```
mvn clean verify jacoco:report
```
### Run the JaCoCo Report and SonarQube
```sh
mvn clean verify sonar:sonar -Dsonar.scm.provider=git -Dsonar.projectKey=key_project -Dsonar.host.url=http://localhost:9000 -Dsonar.login=seu_token jacoco:report
```

### 📌 Running Tests 🧪
To run the tests, use the following command:
```
mvn test
```

### 🚧 O projeto está em desenvolvimento! 🚧
Sugestões, contribuições e feedbacks são bem-vindos! 🙌