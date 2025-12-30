# coupon-api

Gerenciador de cupons

- Tecnologias
  
  Java 21
  
  Spring Boot 3
  
  H2 / PostgreSQL (opcional)
  
  Flyway
  
  Docker / Docker Compose
  
  JUnit 5 + Mockito


- Funcionalidades
  
  Criar cupom (POST /coupon)
  
    Campos obrigatórios: code, description, discountValue, expirationDate
  
    Validações de negócio:
  
      Código normalizado (alfa numérico, maiúsculo, max 20 caracteres)
  
      Desconto mínimo: 0,5
  
      Data de expiração não pode estar no passado
  
    Pode ser criado como publicado ou não

  
  Buscar cupom por ID (GET /coupon/{id})
  
    Retorna dados do cupom, lançando exceções se não existir ou já estiver deletado
  
  
  Deletar cupom (DELETE /coupon/{id})
  
    Soft delete: altera status para DELETED, não remove do banco
  
    Não permite deletar novamente um cupom já deletado
  
  
- O projeto segue princípios de Clean Architecture e SOLID, separando responsabilidades por camada:
  
  <img width="194" height="381" alt="image" src="https://github.com/user-attachments/assets/8886b37f-4731-463a-b186-34a9cbf8cfc1" />
  
  Controller: recebe requisições e envia para o serviço
  
  Service: aplica regras de negócio e orquestra operações
  
  Domain/Model: encapsula todas as regras do cupom
  
  Gateway/Repository: abstrai acesso ao banco
  
  Entity: mapeamento JPA para persistência
  
  Flyway: gerencia scripts de criação e migração de banco

  
- Banco de Dados
  
  Por padrão usa H2 em memória, mas pode ser configurado para PostgreSQL ou Oracle
  
  Scripts de criação e inserção estão em src/main/resources/db/migration com versionamento Flyway
  

- Docker & Docker Compose
  
  Dockerfile
  
    Base: eclipse-temurin:21-jdk
  
    Copia o JAR da aplicação para o container
  
    Expõe a porta 8080
  

  docker-compose.yml
  
    Serviços:
  
      app: container da API
  
      db (opcional): container do PostgreSQL
  
    Permite rodar aplicação e banco juntos com um comando:
  
      docker-compose up --build
  

- A aplicação estará disponível em: http://localhost:8080
  
  
- Endpoints REST
  
  Método - Endpoint - Descrição
  
  POST: /coupon - Criar cupom
  
  GET: /coupon/{id} - Buscar cupom por ID
  
  DELETE: /coupon/{id} - Soft delete do cupom
  
  
  Exemplo de request POST /coupon:
  
    {
  
      "code": "ABC-123",
  
      "description": "Cupom de teste",
  
      "discountValue": 0.8,
  
      "expirationDate": "2025-11-04T17:14:45.180Z",
  
      "published": false
  
    }
  

- Testes
  
  Testes unitários cobrindo regras de negócio (80%)
  
  Mock do CouponGateway para isolar lógica do serviço
  
  Cenários testados:
  
    Criar cupom válido
  
    Criar cupom inválido (código, desconto, data)
  
    Buscar cupom existente, não existente ou deletado
  
    Deletar cupom com soft delete
  
- Como rodar local
  Com Maven
    mvn clean package
    java -jar target/coupon-api-0.0.1-SNAPSHOT.jar

  Com Docker
    docker-compose up --build
