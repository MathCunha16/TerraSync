# 🌱 TerraSync

Sistema web de monitoramento agrícola baseado em IoT com sensores e dashboards para apoio a decisões no campo.

---

## 📁 Estrutura

```
TerraSync/
├── terrasync-backend/     # API REST - Spring Boot 3.5.5 + Java 21
├── terrasync-frontend/    # Interface Web - Angular + TypeScript (em desenvolvimento)
├── docker-compose.yml     # Orquestração
└── .env.example          # Template de variáveis
```

---

## 🚀 Quick Start

```bash
# Clone e configure
git clone https://github.com/MathCunha16/TerraSync.git
cd TerraSync
cp .env.example .env

# Edite o .env com suas credenciais
nano .env

# Suba tudo
docker-compose up --build
```

**Pronto!** API rodando em `http://localhost:8080`

---

## ⚙️ Stack

### Backend ✅
- Java 21 + Spring Boot 3.5.5
- PostgreSQL 17 + PostGIS 3.5
- Flyway + JPA/Hibernate
- MapStruct + Gradle

### Frontend 🚧
- Angular + TypeScript
- (Em desenvolvimento)

---

## 🐳 Docker Compose

O `docker-compose.yml` gerencia 2 serviços conectados na rede `terra_sync_network`:

### 🐘 postgres-database
- **Container**: `terrasync_db`
- **Imagem**: `postgis/postgis:17-3.5` (PostgreSQL + extensão PostGIS)
- **Porta**: `5432` (definida no `.env` via `DB_HOST_PORT`)
- **Persistência**: Volume `postgres_data` para não perder dados ao recriar containers
- **Variáveis**: Lê `DB_USERNAME`, `DB_PASSWORD`, `DB_NAME` do `.env`

### 🚀 backend-api
- **Container**: `terrasync_api`
- **Build**: Usa o `Dockerfile` de `./terrasync-backend`
- **Porta**: `8080`
- **Depende de**: `postgres-database` (aguarda o banco subir antes)
- **Conexão**: Conecta no banco via hostname `postgres-database` (DNS interno do Docker)

**Importante**: As variáveis do `.env` devem estar na **raiz do projeto**, não dentro de `terrasync-backend`.

---

## 🔧 Comandos Docker

```bash
# Subir todos os serviços
docker-compose up --build          # Rebuild + sobe (use após mudanças no código)
docker-compose up -d               # Sobe em background (detached)

# Gerenciar
docker-compose logs -f             # Acompanha logs em tempo real
docker-compose logs -f backend-api # Logs apenas da API
docker-compose ps                  # Lista containers rodando
docker-compose down                # Para todos os serviços
docker-compose down -v             # Para e APAGA volumes (⚠️ deleta dados do banco)

# Desenvolvimento
docker-compose up postgres-database           # Sobe apenas o banco (útil para rodar API local)
docker-compose restart backend-api            # Reinicia apenas a API
docker exec -it terrasync_db psql -U postgres -d terrasync_db  # Acessa o banco via CLI
```

---

## 🌳 Workflow de Branches

### ⚠️ REGRAS IMPORTANTES

```diff
+ ✅ SEMPRE trabalhe em branches
+ ✅ SEMPRE abra Pull Requests para revisão
- ❌ NUNCA faça commit direto na main
- ❌ NUNCA dê push --force na main
```

### Fluxo de Trabalho

#### 1️⃣ Antes de começar qualquer tarefa

```bash
# Certifique-se de estar na main atualizada
git checkout main
git pull origin main
```

#### 2️⃣ Criar sua branch

```bash
# Para novas funcionalidades
git checkout -b feature/nome-descritivo

# Para correções de bugs
git checkout -b fix/nome-do-bug

# Para ajustes de documentação
git checkout -b docs/o-que-mudou

# Exemplos:
git checkout -b feature/crud-sensors
git checkout -b fix/farm-active-bug
git checkout -b docs/update-backend-readme
```

#### 3️⃣ Trabalhe normalmente

```bash
# Faça suas mudanças...
# Depois commit:
git add .
git commit -m "feat: adiciona CRUD de sensores"

# Mais mudanças...
git commit -m "fix: corrige validação de email"
```

#### 4️⃣ Envie sua branch

```bash
# Push da sua branch (não da main!)
git push origin feature/nome-descritivo

# Se for o primeiro push da branch:
git push -u origin feature/nome-descritivo
```

#### 5️⃣ Abra um Pull Request

1. Vá no GitHub
2. Clique em "Compare & pull request"
3. Descreva o que você fez
4. Aguarde revisão do @MathCunha16 ou outro membro

#### 6️⃣ Depois do merge

```bash
# Volte pra main e atualize
git checkout main
git pull origin main

# Delete sua branch local (opcional, mas recomendado)
git branch -d feature/nome-descritivo
```

### 📝 Padrão de Commits (Conventional Commits)

Use sempre o prefixo adequado:

```bash
feat:     # Nova funcionalidade
fix:      # Correção de bug
docs:     # Apenas documentação
refactor: # Refatoração de código (sem mudar comportamento)
test:     # Adiciona ou modifica testes
chore:    # Manutenção, configs, deps

# Exemplos:
git commit -m "feat: adiciona endpoint de sensores"
git commit -m "fix: corrige bug no mapper de Farm"
git commit -m "docs: atualiza README com instruções de deploy"
git commit -m "refactor: simplifica lógica do CreateFarmUseCase"
git commit -m "test: adiciona testes para CropTypeService"
git commit -m "chore: atualiza dependências do gradle"
```

### 🆘 Se você cometeu um erro

```bash
# Se commitou na main SEM dar push ainda:
git reset HEAD~1              # Desfaz o último commit (mantém mudanças)
git checkout -b feature/minha-branch  # Cria branch correta
git add .
git commit -m "feat: minha feature"

# Se já deu push na main (⚠️ AVISE O @MathCunha16 IMEDIATAMENTE):
# NÃO tente consertar sozinho, chame ajuda!
```

---

## 📚 Docs

- **[Backend README](terrasync-backend/README.md)** - Arquitetura, migrations, use cases, gradle, sonarqube
- **Frontend README** - (em breve)

---

## 🛠️ SonarQube

Repositórios úteis para análise de código:
- [SonarQube + PostgreSQL](https://github.com/MathCunha16/SonarQube-with-PostgreSQL)
- [SonarQube + Branch Plugin](https://github.com/MathCunha16/docker-compose-sonarqube-branch-plugin-with-postgres)

```bash
cd terrasync-backend
./sonar-scan.sh
```

---

## 🛣️ Status do Projeto

**🚧 Fazendo:**
- Backend API

**🚧 Fazendo:**
- Frontend Angular

**🔜 Próximo:**
- Sistema de Alertas
- Dashboards
- WebSockets para IoT
