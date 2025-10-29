# --- ESTÁGIO 1: O "CONSTRUTOR" (BUILDER) ---
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# 1. Copia os arquivos de build para otimizar o cache
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Fix line endings e permissões
RUN sed -i 's/\r$//' ./gradlew
RUN chmod +x ./gradlew

# 2. Roda um comando que APENAS baixa as dependências, criando camada de cache
RUN ./gradlew dependencies --no-daemon

# 3. Copia o resto do código-fonte
COPY src ./src

# 4. Constrói o jar final e skippa os testes
RUN ./gradlew bootJar --no-daemon

# --- ESTÁGIO 2: A IMAGEM FINAL (FINAL IMAGE) ---
# --- BEM MAIS LEVE E OTIMIZADA ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar TerraSyncApi.jar

EXPOSE 8080

# Setando o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "TerraSyncApi.jar"]