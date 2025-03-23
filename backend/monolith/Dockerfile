FROM amazoncorretto:21-alpine
# for maven
ARG JAR_FILE=app/target/*.jar
# for gradle
# ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "/app.jar"]
