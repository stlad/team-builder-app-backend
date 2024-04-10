FROM openjdk:19-alpine
ARG JAR_FILE=tba-mcs-hardskills-0.0.1.jar
COPY ${JAR_FILE} hardskills.jar
ENTRYPOINT ["java", "-jar", "/hardskills.jar"]