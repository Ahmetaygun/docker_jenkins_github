# İmajın temel olarak hangi imajdan türetileceğini belirtin
FROM alpine:latest

# İmajda çalışacak komutları ekleyin
RUN apk add --update bash


FROM openjdk:21
ADD target/odev2.jar odev2.jar
ENTRYPOINT ["java","-jar","/odev2.jar"]
