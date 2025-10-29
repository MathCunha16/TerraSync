# 🌱 TerraSync Backend - API REST

Backend da aplicação **TerraSync**, um sistema web de monitoramento agrícola baseado em IoT para gerenciamento de fazendas, culturas e sensores inteligentes.

## 📋 Índice

- [Visão Geral](#-visão-geral)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Configuração do Ambiente](#-configuração-do-ambiente)
- [Executando o Projeto](#-executando-o-projeto)
- [Estrutura de Pacotes](#-estrutura-de-pacotes)
- [Arquitetura: Abordagem com Use Cases](#-arquitetura-abordagem-com-use-cases)
- [Banco de Dados](#-banco-de-dados)
- [Análise de Código com SonarQube](#-análise-de-código-com-sonarqube)
- [Gradle: Comandos Úteis](#-gradle-comandos-úteis)
- [Docker](#-docker)
- [API Endpoints](#-api-endpoints)

---

## 🎯 Visão Geral

O **TerraSync Backend** é uma API RESTful construída com **Spring Boot 3.5.5** e **Java 21**, projetada para gerenciar:

- 👥 **Usuários**: Gerenciamento de contas e autenticação
- 🚜 **Fazendas (Farms)**: Propriedades rurais com geolocalização (PostGIS)
- 🌾 **Tipos de Culturas (Crop Types)**: Catálogo de culturas (milho, soja, café, etc.)
- 🌱 **Culturas (Crops)**: Plantações específicas com ciclo de vida e status
- 📡 **Sensores IoT**: Dispositivos de monitoramento (temperatura, umidade, pH, luminosidade)
- 📊 **Dados de Sensores**: Leituras em tempo real armazenadas em JSON
- 🚨 **Alertas**: Sistema de notificações baseado em regras de sensores

---

## 🛠 Tecnologias

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 21 | Linguagem de programação |
| **Spring Boot** | 3.5.5 | Framework principal |
| **Spring Data JPA** | - | Camada de persistência |
| **Spring Security** | - | Segurança e autenticação |
| **PostgreSQL** | 17 | Banco de dados relacional |
| **PostGIS** | 3.5 | Extensão para dados geoespaciais |
| **Flyway** | - | Versionamento de banco de dados |
| **Hibernate Spatial** | - | Suporte a tipos geométricos |
| **MapStruct** | 1.5.5 | Mapeamento de DTOs |
| **Gradle** | Wrapper | Gerenciador de dependências |
| **Docker** | - | Containerização |
| **SonarQube** | - | Análise estática de código |
| **JaCoCo** | 0.8.13 | Cobertura de testes |

---

## 📦 Pré-requisitos

Certifique-se de ter instalado:

- **Java 21** ([Eclipse Temurin](https://adoptium.net/) recomendado)
- **Docker** e **Docker Compose** (para rodar PostgreSQL e a aplicação containerizada)
- **Git** (para clonar o repositório)
- **(Opcional)** **Gradle** instalado localmente (ou use o wrapper `./gradlew`)

---

## ⚙️ Configuração do Ambiente

### 1. Clonar o Repositório

```bash
git clone https://github.com/MathCunha16/TerraSync.git
cd TerraSync
```

### 2. Configurar Variáveis de Ambiente

Crie um arquivo `.env` na **raiz do projeto** (não dentro de `terrasync-backend`) com as seguintes variáveis:

```env
# Configurações do Banco de Dados
DB_NAME=terrasync_db
DB_USERNAME=postgres
DB_PASSWORD=sua_senha_super_secreta
DB_HOST_PORT=5432

# Token do SonarQube (opcional, apenas se for usar análise de código)
SONAR_TOKEN=seu_token_sonarqube_aqui
```

> **⚠️ IMPORTANTE**: O arquivo `.env` deve estar na raiz do projeto para ser lido corretamente pelo `docker-compose.yml`.

---

## 🚀 Executando o Projeto

### Opção 1: Usando Docker Compose (Recomendado)

Esta é a forma mais simples de rodar o projeto completo (banco de dados + API).

```bash
# Na raiz do projeto
docker-compose up --build
```

A API estará disponível em: **http://localhost:8080**

Para parar os containers:

```bash
docker-compose down
```

Para parar e **remover os volumes** (apaga os dados do banco):

```bash
docker-compose down -v
```

---

### Opção 2: Rodando Localmente (Desenvolvimento)

#### 2.1. Subir apenas o Banco de Dados

```bash
docker-compose up postgres-database
```

#### 2.2. Rodar a aplicação com Gradle

```bash
cd terrasync-backend

# Linux/Mac
./gradlew bootRun

# Windows
.\gradlew.bat bootRun
```

A API estará disponível em: **http://localhost:8080**

---

## 📁 Estrutura de Pacotes

```
terrasync-backend/src/main/java/com/terrasync/backend/
│
├── config/                    # Configurações do Spring
│   ├── security/              # Configurações de segurança
│   └── seed/                  # Seeds de dados para desenvolvimento
│
├── controller/                # Controllers REST (Camada de Apresentação)
│   ├── FarmController.java
│   ├── CropController.java
│   ├── CropTypeController.java
│   └── ...
│
├── dto/                       # Data Transfer Objects
│   ├── farm/
│   ├── crop/
│   └── cropType/
│
├── entity/                    # Entidades JPA (Camada de Domínio)
│   ├── User.java
│   ├── Farm.java
│   ├── Crop.java
│   ├── CropType.java
│   ├── Sensor.java
│   ├── SensorData.java
│   ├── Alert.java
│   ├── base/                  # Classes base (ex: BaseEntity)
│   └── enums/                 # Enums do domínio
│
├── exception/                 # Exceções customizadas
│   ├── domain/                # Exceções de domínio
│   └── handler/               # Handlers globais de exceção
│
├── mapper/                    # MapStruct Mappers (DTO ↔ Entity)
│   ├── FarmMapper.java
│   ├── CropMapper.java
│   └── ...
│
├── repository/                # Repositórios JPA (Camada de Persistência)
│   ├── FarmRepository.java
│   ├── CropRepository.java
│   └── ...
│
├── service/                   # Camada de Serviços (Orquestração dos Use Cases)
│   ├── farm/
│   │   ├── FarmServices.java  # Orquestrador principal
│   │   └── useCases/          # Use Cases específicos
│   │       ├── CreateFarmUseCase.java
│   │       ├── FindFarmByIdUseCase.java
│   │       ├── FindAllFarmsByUserUseCase.java
│   │       ├── UpdateFarmUseCase.java
│   │       └── DeleteFarmUseCase.java
│   │
│   ├── crop/
│   │   ├── CropServices.java
│   │   └── useCases/
│   │
│   ├── cropType/
│   │   ├── CropTypeServices.java
│   │   └── useCases/
│   │
│   └── ...
│
└── TerrasyncBackendApplication.java  # Classe principal
```

---

## 🏗 Arquitetura: Abordagem com Use Cases

### Por que Use Cases?

Este projeto adota uma **arquitetura orientada a casos de uso**, inspirada em **Clean Architecture** e **DDD (Domain-Driven Design)**. Cada operação de negócio é encapsulada em um **Use Case** independente, promovendo:

- ✅ **Alta Coesão**: Cada Use Case tem uma responsabilidade única e bem definida
- ✅ **Baixo Acoplamento**: Use Cases são independentes entre si
- ✅ **Testabilidade**: Cada Use Case pode ser testado isoladamente
- ✅ **Manutenibilidade**: Fácil localizar e modificar lógica de negócio específica
- ✅ **Escalabilidade**: Novos casos de uso podem ser adicionados sem impactar os existentes

### Estrutura de um Use Case

Cada Use Case segue este padrão:

```java
@Component
public class CreateFarmUseCase {
    
    private final Logger logger = LoggerFactory.getLogger(CreateFarmUseCase.class);
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public CreateFarmUseCase(FarmRepository farmRepository, 
                             UserRepository userRepository, 
                             FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.userRepository = userRepository;
        this.farmMapper = farmMapper;
    }

    public FarmResponseDTO handle(FarmRequestDTO dto, Long userId) {
        logger.info("--------- Trying to Create a new Farm for User ID: {} ---------", userId);
        
        // 1. Validações de negócio
        if (farmRepository.existsByNameIgnoreCaseAndUser_Id(dto.name(), userId)) {
            throw new DuplicateResourceException("User already has a farm with name '" + dto.name() + "'.");
        }

        // 2. Buscar dependências
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));

        // 3. Criar e persistir entidade
        Farm newFarm = farmMapper.toEntity(dto);
        newFarm.setUser(owner);
        Farm savedFarm = farmRepository.save(newFarm);
        
        // 4. Retornar resposta
        return farmMapper.toResponseDTO(savedFarm);
    }
}
```

### Camada de Serviços (Orquestradores)

Os **Services** atuam como **fachadas** que orquestram os Use Cases:

```java
@Service
public class FarmServices {
    
    private final CreateFarmUseCase createFarmUseCase;
    private final FindAllFarmsByUserUseCase findAllFarmsByUserUseCase;
    private final FindFarmByIdUseCase findFarmByIdUseCase;
    private final UpdateFarmUseCase updateFarmUseCase;
    private final DeleteFarmUseCase deleteFarmUseCase;

    @Autowired
    public FarmServices(CreateFarmUseCase createFarmUseCase, 
                        FindAllFarmsByUserUseCase findAllFarmsByUserUseCase,
                        FindFarmByIdUseCase findFarmByIdUseCase, 
                        UpdateFarmUseCase updateFarmUseCase,
                        DeleteFarmUseCase deleteFarmUseCase) {
        // Injeção de dependências
    }

    public FarmResponseDTO createNewFarm(FarmRequestDTO dto, Long userId) {
        return createFarmUseCase.handle(dto, userId);
    }
    
    // Outros métodos delegam para seus respectivos Use Cases
}
```

### Fluxo de uma Requisição

```
┌─────────────┐      ┌────────────┐      ┌─────────────┐      ┌──────────┐      ┌────────────┐
│   Client    │──1──▶│ Controller │──2──▶│  Service    │──3──▶│ Use Case │──4──▶│ Repository │
│  (HTTP)     │      │   (REST)   │      │(Orchestrator)│      │ (Logic)  │      │   (DB)     │
└─────────────┘      └────────────┘      └─────────────┘      └──────────┘      └────────────┘
       ▲                    │                    │                    │                    │
       └────────────────────┴────────────────────┴────────────────────┴────────────────────┘
                                     Response Flow (DTO)
```

### Benefícios na Prática

1. **Reutilização**: Um Use Case pode ser chamado por múltiplos controllers ou serviços
2. **Composição**: Use Cases complexos podem chamar outros Use Cases
3. **Transações**: Cada Use Case pode ter sua própria estratégia transacional
4. **Logging**: Logs centralizados e rastreáveis por operação
5. **Segurança**: Validações de autorização isoladas por caso de uso

---

## 🗄 Banco de Dados

### Tecnologias

- **PostgreSQL 17** com extensão **PostGIS 3.5** (para dados geoespaciais)
- **Flyway** para versionamento de migrations

### Migrations

As migrations estão localizadas em: `terrasync-backend/src/main/resources/db/migration/`

#### Ordem de Execução:

1. **V1__create_enums.sql** - Cria tipos ENUM (sensor_type, crop_status, alert_status)
2. **V2__create_users_table.sql** - Tabela de usuários
3. **V3__create_farms_table.sql** - Tabela de fazendas (com geolocalização PostGIS)
4. **V4__create_crop_types_table.sql** - Tabela de tipos de culturas
5. **V5__create_crops_table.sql** - Tabela de culturas
6. **V6__create_sensors_table.sql** - Tabela de sensores IoT
7. **V7__create_sensor_data_table.sql** - Tabela de dados de sensores (JSONB)
8. **V8__create_alerts_table.sql** - Tabela de alertas
9. **V9__create_indexes.sql** - Índices para otimização de queries
10. **V10__create_updated_at_trigger.sql** - Trigger para atualização automática de `updated_at`

### Modelo de Dados (Resumo)

```
Users (usuários)
  └── Farms (fazendas)
       └── Crops (culturas)
            └── Sensors (sensores)
                 ├── Sensor Data (leituras)
                 └── Alerts (alertas)

CropTypes (tipos de cultura) ──▶ Crops
```

### Tipos de Sensores

- `TEMPERATURE` - Sensor de temperatura
- `SOIL_MOISTURE` - Sensor de umidade do solo
- `PH` - Sensor de pH
- `LIGHT` - Sensor de luminosidade

### Status de Culturas

- `PLANTED` - Plantada
- `GROWING` - Em crescimento
- `HARVEST_READY` - Pronta para colheita
- `HARVESTED` - Colhida
- `PAUSED` - Pausada

### Status de Alertas

- `OPEN` - Alerta aberto
- `RESOLVED` - Alerta resolvido

### Executar Migrations Manualmente

```bash
./gradlew flywayMigrate
```

### Limpar Banco e Recriar

```bash
./gradlew flywayClean flywayMigrate
```

---

## 📊 Análise de Código com SonarQube

O projeto está configurado para análise estática de código com **SonarQube** e cobertura de testes com **JaCoCo**.

### Repositórios de SonarQube

Para facilitar o setup do SonarQube, utilize um destes repositórios:

- **[SonarQube com PostgreSQL](https://github.com/MathCunha16/SonarQube-with-PostgreSQL)** - Setup básico com persistência
- **[SonarQube com Branch Plugin](https://github.com/MathCunha16/docker-compose-sonarqube-branch-plugin-with-postgres)** - Suporte a análise multi-branch

### Script de Análise: `sonar-scan.sh`

O projeto inclui um script shell para facilitar a execução do SonarQube:

**Localização**: `terrasync-backend/sonar-scan.sh`

#### O que o script faz:

1. 🔍 **Detecta automaticamente a branch Git atual**
2. 🔑 **Lê o token do SonarQube do arquivo `.env`**
3. 🏗️ **Compila o projeto** (pulando testes para agilizar)
4. 📊 **Executa análise no SonarQube** com os parâmetros da branch

#### Como usar:

```bash
cd terrasync-backend

# Dar permissão de execução (apenas primeira vez)
chmod +x sonar-scan.sh

# Executar análise
./sonar-scan.sh
```

#### Configuração Necessária

Adicione o token do SonarQube no arquivo `.env` na raiz do projeto:

```env
SONAR_TOKEN=seu_token_aqui
```

### Acessar Resultados

Após a execução, acesse: **http://localhost:9000**

- **Projeto**: TerraSync
- **Cobertura de Código**: Configurada para mínimo de 80% (JaCoCo)

---

## 🔧 Gradle: Comandos Úteis

### Build e Execução

```bash
# Compilar o projeto
./gradlew build

# Compilar sem executar testes
./gradlew build -x test

# Executar a aplicação
./gradlew bootRun

# Gerar JAR executável
./gradlew bootJar
# Arquivo gerado em: build/libs/terrasync-backend-0.0.1-SNAPSHOT.jar

# Executar o JAR
java -jar build/libs/terrasync-backend-0.0.1-SNAPSHOT.jar
```

### Testes (em desenvolvimento)

```bash
# Executar testes
./gradlew test

# Gerar relatório de cobertura JaCoCo
./gradlew jacocoTestReport
# Relatório em: build/reports/jacoco/test/html/index.html

# Verificar cobertura mínima (80%)
./gradlew jacocoTestCoverageVerification
```

### Limpeza

```bash
# Limpar build
./gradlew clean

# Limpar e recompilar
./gradlew clean build
```

### Dependências

```bash
# Listar dependências
./gradlew dependencies

# Atualizar dependências
./gradlew dependencies --refresh-dependencies
```

---

## 🐳 Docker

### Dockerfile Multi-Stage

O projeto usa um **Dockerfile multi-stage** para otimização:

#### Estágio 1: Builder
- Usa `eclipse-temurin:21-jdk`
- Copia arquivos Gradle e baixa dependências (cached)
- Compila o projeto e gera o JAR

#### Estágio 2: Runtime
- Usa `eclipse-temurin:21-jre-alpine` (imagem leve)
- Copia apenas o JAR final
- Expõe porta 8080
- Executa a aplicação

**Vantagens**:
- ✅ Imagem final **muito menor** (~200MB vs ~800MB)
- ✅ Melhor **segurança** (sem ferramentas de build em produção)
- ✅ **Cache de layers** otimizado (dependências raramente mudam)

### Build Manual da Imagem

```bash
cd terrasync-backend
docker build -t terrasync-api:latest .
```

### Executar Container Isolado

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/terrasync_db \
  -e SPRING_DATASOURCE_USERNAME=postgres \
  -e SPRING_DATASOURCE_PASSWORD=sua_senha \
  terrasync-api:latest
```

### Docker Compose

O `docker-compose.yml` na raiz do projeto orquestra:

1. **postgres-database**: PostgreSQL 17 + PostGIS 3.5
2. **backend-api**: API Spring Boot

```bash
# Subir todos os serviços
docker-compose up -d

# Ver logs
docker-compose logs -f backend-api

# Parar serviços
docker-compose down

# Rebuild e restart
docker-compose up --build
```

---

## 🌐 API Endpoints

### Base URL

```
http://localhost:8080/api/v1
```

### Endpoints Disponíveis

#### 🚜 Farms (Fazendas)

```http
POST   /farm           # Criar nova fazenda
GET    /farm/user/{userId}  # Listar fazendas de um usuário
GET    /farm/{farmId}       # Buscar fazenda por ID
PUT    /farm/{farmId}       # Atualizar fazenda
DELETE /farm/{farmId}       # Desativar fazenda (soft delete)
```

#### 🌾 Crop Types (Tipos de Cultura)

```http
POST   /crop-type       # Criar tipo de cultura
GET    /crop-type       # Listar todos os tipos
GET    /crop-type/{id}  # Buscar tipo por ID
PUT    /crop-type/{id}  # Atualizar tipo
DELETE /crop-type/{id}  # Deletar tipo
```

#### 🌱 Crops (Culturas)

```http
POST   /crop            # Criar cultura (em desenvolvimento)
```

### Exemplo de Requisição

```bash
# Criar uma fazenda
curl -X POST http://localhost:8080/api/v1/farm \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Fazenda Santa Maria",
    "sizeInHectares": 150.50,
    "country": "Brasil",
    "state": "São Paulo",
    "city": "Campinas",
    "geolocation": {
      "latitude": -22.9099,
      "longitude": -47.0626
    }
  }'
```

### Health Check

```bash
# Verificar saúde da aplicação
curl http://localhost:8080/actuator/health
```

---

## 📝 Notas Importantes

### 🚧 TODOs e Funcionalidades em Desenvolvimento

- **Autenticação JWT**: Atualmente usando `userId = 1L` hardcoded nos controllers
- **Testes Unitários**: Cobertura de testes ainda não implementada
- **Paginação**: Endpoints de listagem sem paginação
- **HATEOAS**: Links de navegação não implementados
- **XSS Protection**: Proteção contra Cross-Site Scripting em desenvolvimento
- **Validação de Deleção**: CropTypes podem ser deletados mesmo com Crops vinculados

### 🐛 Bugs Conhecidos

- **isActive sempre false**: Bug no CreateFarmUseCase ao criar fazendas (prioridade máxima)

---

## 🤝 Contribuindo

1. Crie uma branch para sua feature: `git checkout -b feature/minha-feature`
2. Commit suas mudanças: `git commit -m 'feat: adiciona nova feature'`
3. Push para a branch: `git push origin feature/minha-feature`
4. Abra um Pull Request

---

## 📄 Licença

Este projeto é parte do TerraSync e está sob desenvolvimento acadêmico/educacional.

---

## 📚 Recursos Adicionais

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [PostgreSQL + PostGIS Guide](https://postgis.net/documentation/)
- [MapStruct Documentation](https://mapstruct.org/)
- [Flyway Documentation](https://flywaydb.org/documentation/)

---

**Deus Seja Louvado**