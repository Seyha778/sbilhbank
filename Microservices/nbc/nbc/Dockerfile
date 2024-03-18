FROM openjdk:21

EXPOSE 9040
ARG JAR_FILE=target/nbc-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]