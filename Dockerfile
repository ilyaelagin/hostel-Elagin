FROM openjdk:16
ARG JAR_FILE=target/hostel-Elagin-1.0.0.jar
WORKDIR /opt/app
COPY ${JAR_FILE} hostel-Elagin-1.0.0.jar
ENTRYPOINT ["java", "-jar", "hostel-Elagin-1.0.0.jar", "--spring.activemq.broker-url=tcp://activemqcontainer:61616"]