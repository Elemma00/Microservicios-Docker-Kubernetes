FROM openjdk:21-jdk-slim AS builder

WORKDIR /app/msvc-cursos

COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN sed -i -e 's/\r$//' ./mvnw

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/msvc-cursos/target/msvc-cursos*.jar .

EXPOSE 8002

# Copiar el script de entrada
COPY entrypoint.sh /entrypoint.sh

# Dar permisos de ejecución al script
RUN chmod +x /entrypoint.sh

# Usar el script como ENTRYPOINT
ENTRYPOINT ["/entrypoint.sh"]

# ENTRYPOINT ["java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]