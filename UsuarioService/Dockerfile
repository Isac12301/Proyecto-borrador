# ========================
# Dockerfile - UsuarioService
# ========================
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

# Copiar el pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Compilar el proyecto (usa el wrapper de Maven incluido)
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Imagen final para ejecución
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/UsuarioService-0.0.1-SNAPSHOT.jar app.jar

# Puerto del servicio
EXPOSE 8110

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
