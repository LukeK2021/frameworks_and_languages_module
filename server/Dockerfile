FROM eclipse-temurin:11-jdk-focal
EXPOSE 8000
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline 
COPY src ./src
CMD ["./mvnw", "spring-boot:run","-Dspring-boot.run.arguments=--server.port=8000"]