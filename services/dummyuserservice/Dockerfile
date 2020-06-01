FROM openjdk:11 AS BUILD_IMAGE
ENV APP_HOME=/root/myapp/
RUN mkdir -p $APP_HOME/src/main/java
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME
COPY gradle $APP_HOME/gradle
COPY . .
RUN ./gradlew build

FROM openjdk:11-jre
WORKDIR /root/myapp
COPY --from=BUILD_IMAGE /root/myapp/build/libs/dummyuserservice-0.1-all.jar .
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx512m", "-jar", "dummyuserservice-0.1-all.jar"]
