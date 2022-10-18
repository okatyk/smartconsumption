FROM gradle:6.5.0-jdk14 as build
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle clean build bootJar --parallel

COPY --from=build /src/application/build/libs/app.jar /app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=test","/app.jar"]