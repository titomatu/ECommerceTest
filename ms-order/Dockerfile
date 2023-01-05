FROM openjdk:11
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY target/ms-order.jar app.jar
EXPOSE 8089
CMD ["java", "-jar", "app.jar"]
