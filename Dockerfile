FROM openjdk:21
ADD target/odev2.jar odev2.jar
ENTRYPOINT ["java","-jar","/odev2.jar"]
