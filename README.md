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


Arquivo docker-compose.yml
O arquivo docker-compose.yml é utilizado para definir e gerenciar os serviços Docker necessários para o projeto. No nosso caso, estamos utilizando um contêiner PostgreSQL para o banco de dados.
```textplain
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

📌 Variáveis de Ambiente
Crie um arquivo .env na raiz do projeto com as seguintes variáveis:

```textplain
POSTGRES_DB=order_processing_db
POSTGRES_USER=order_user
POSTGRES_PASSWORD=securepassword
```

🚧 O projeto está em desenvolvimento! 🚧
Sugestões, contribuições e feedbacks são bem-vindos! 🙌