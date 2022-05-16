FROM openjdk:11
COPY ./target/airbnb-clone-back.jar /usr/app/
WORKDIR /usr/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-DAPP_NAME=airbnb-clone-back","-Dspring.profiles.active=docker" , "./app/airbnb-clone-back.jar"]

