FROM maven:3.6.3-jdk-14 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

FROM openjdk:14

LABEL maintainer="joao.calvet.siqueira@gmail.com"

EXPOSE 8080
COPY --from=build /build/target/estoque-api.jar /app/estoque-api.jar
CMD exec java $JAVA_OPTS -jar /app/estoque-api.jar