FROM openjdk:19-alpine
ARG JAR_FILE=tba-mcs-teambuilder-0.0.1.jar
COPY ${JAR_FILE} teambuilder.jar
ENTRYPOINT ["java", "-jar", "/teambuilder.jar"]