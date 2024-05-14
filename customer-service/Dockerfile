FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ENV CUSTOMER_SERVICE_CLIENT_KEYCLOAK_SECRET=95515ec0-8593-4a49-a7ad-b91c680e9ba9
ENV STRIPE_API_KEY=sk_test_51LpDu1K29xxBejuG3ij6MrEizxCIZ4CteecHLrAoRxMQtCxxvJ0N4XlxX1XjzgRyhrZ4U6Q3Ryr7ILW3n29pJnRL0042hfyOnH
ENV SMTP_GMAIL_USERNAME=moodluxe.store
ENV SMTP_GMAIL_PASSWORD=rmvtsrmdikngeyij
ENV DB_USERNAME=moodluxe
ENV DB_PASSWORD=moodluxe
ENV POSTGRES_HOST=postgres-moodluxe
ENV POSTGRES_PORT=5432
ENV EUREKA_HOST=discovery-service
ENV EUREKA_PORT=8761
COPY target/*.jar customer-service.jar
ENTRYPOINT ["java","-XX:+UseContainerSupport","-XX:MaxRAM=512m","-jar","/customer-service.jar"]
