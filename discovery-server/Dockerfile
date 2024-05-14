FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/*.jar discovery-service.jar
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAM=512m","-jar","/discovery-service.jar"]
