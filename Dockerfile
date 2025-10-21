## build stage ##
FROM maven:3.5.3-jdk-8-alpine AS build

WORKDIR /app
COPY . .
RUN mvn install -DskipTests=true

## run stage ##
FROM alpine:3.19

RUN adduser -D QuanLyTuyenDung

RUN apk add openjdk8

WORKDIR /run
COPY --from=build /app/target/QuanLyTuyenDung-0.0.1-SNAPSHOT.jar /run/QuanLyTuyenDung-0.0.1-SNAPSHOT.jar

RUN chown -R QuanLyTuyenDung:QuanLyTuyenDung /run

USER QuanLyTuyenDung

EXPOSE 8080

ENTRYPOINT java -jar /run/QuanLyTuyenDung-0.0.1-SNAPSHOT.jar
