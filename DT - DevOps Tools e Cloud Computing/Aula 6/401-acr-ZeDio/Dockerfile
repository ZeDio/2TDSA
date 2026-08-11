FROM eclipse-temurin:21 AS build

WORKDIR /app

COPY . .

RUN ./gradlew clean bootJar --no-daemon

# A partir daqui, é a imagem de execução
FROM gcr.io/distroless/java21:nonroot AS run

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]