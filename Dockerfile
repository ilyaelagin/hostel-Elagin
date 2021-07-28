FROM openjdk
RUN mkdir -p /usr/src/hostel
WORKDIR /usr/src/hostel
ARG JAR_FILE=target/hostel-Elagin-1.0.0.jar
COPY ${JAR_FILE} hostel-Elagin-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "hostel-Elagin-1.0.0.jar"]