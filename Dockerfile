FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY receipt.processor/.mvn .mvn
COPY receipt.processor/mvnw receipt.processor/pom.xml ./
RUN ./mvnw dependency:go-offline

COPY receipt.processor/src ./src

CMD ["./mvnw", "spring-boot:run"]