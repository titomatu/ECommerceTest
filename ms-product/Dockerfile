FROM openjdk:11
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/ms-product.jar app.jar
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]
