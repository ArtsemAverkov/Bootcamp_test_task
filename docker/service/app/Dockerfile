#FROM openjdk:11
#ADD module-controller-1.0-SNAPSHOT.jar module.jar
#ENTRYPOINT ["java", "-jar", "module.jar"]

FROM openjdk:11-jre-slim
WORKDIR /app
ADD module-controller-1.0-SNAPSHOT.jar module.jar
CMD java -jar ./module.jar
