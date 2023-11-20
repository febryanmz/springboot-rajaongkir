FROM maven:3.8.1-openjdk-17-slim AS builder
ENV DEBIAN_FRONTEND noninteractive

RUN mkdir -p /app/raja-ongkir
WORKDIR /app
COPY ./ /app/raja-ongkir/
WORKDIR /app/raja-ongkir

RUN mvn -Dmaven.test.skip=true clean package

FROM eclipse-temurin:17.0.7_7-jre-alpine
ENV DEBIAN_FRONTEND noninteractive

RUN mkdir -p /app/logs
WORKDIR /app

COPY --from=builder /app/raja-ongkir/target/raja-ongkir-0.0.1-SNAPSHOT.jar .
#COPY --from=builder /app/raja-ongkir/timezone /etc
EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar","raja-ongkir-0.0.1-SNAPSHOT.jar"]