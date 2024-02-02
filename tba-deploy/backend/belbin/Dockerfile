FROM openjdk:19-alpine
ARG JAR_FILE=tba-mcs-belbin-0.0.1.jar
COPY ${JAR_FILE} belbin.jar
ENTRYPOINT ["java", "-jar", "/belbin.jar"]