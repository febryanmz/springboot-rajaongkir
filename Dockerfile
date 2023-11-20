FROM openjdk:17-alpine

WORKDIR /app

COPY target/raja-ongkir-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 5000

CMD ["java", "-jar", "app.jar"]
