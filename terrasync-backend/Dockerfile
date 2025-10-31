# Estágio 1: Dependencies
FROM eclipse-temurin:21-jdk AS dependencies

WORKDIR /app

# Copia os arquivos de configuração do Gradle
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Fix line endings e permissões
RUN sed -i 's/\r$//' ./gradlew && chmod +x ./gradlew

# Baixa as dependências (cria uma camada de cache)
RUN ./gradlew dependencies --no-daemon

# Estágio 2: Builder
FROM dependencies AS builder

#Copia o código fonte
COPY src ./src

#Compila o JAR (Sem rodar os testes)
RUN ./gradlew bootJar --no-daemon

# Estágio 3: Production
FROM eclipse-temurin:21-jre-alpine AS production

WORKDIR /app

#Copia apenas o JAR compilado no estágio builder
COPY --from=builder /app/build/libs/*.jar TerraSyncApi.jar

EXPOSE 8080

# Comando padrão para rodar a aplicação
CMD ["java", "-jar", "TerraSyncApi.jar"]

# Estágio 4: Test
FROM dependencies AS test

# Copia o código fonte
COPY src ./src

# Comando padrão: rodar os testes
CMD ["./gradlew", "test", "--no-daemon"]