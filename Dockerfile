FROM gradle:9.2.1-jdk17

WORKDIR /home/gradle/project

COPY . .

EXPOSE 8080

CMD ["./gradlew", "bootRun"]
