FROM amazoncorretto:21-alpine

ARG JAR_FILE=accounts-impl/target/*.jar

COPY ${JAR_FILE} service.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001", "-jar", "/service.jar"]
