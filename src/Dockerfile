FROM opendjdk:17
COPY target/user-api.jar /usr/app
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","user-api.jar"]