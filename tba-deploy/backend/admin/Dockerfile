FROM openjdk:19-alpine
ARG JAR_FILE=tba-mcs-admin-0.0.1.jar
COPY ${JAR_FILE} admin.jar
ENTRYPOINT ["java", "-jar", "/admin.jar"]