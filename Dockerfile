FROM openjdk:14

COPY ./Java_Architect_Spring/WebContent/META-INF/Java_Architect_Spring-0.0.1-SNAPSHOT.jar /root/Java_Architect_Spring-0.0.1-SNAPSHOT.jar

CMD [ "sh", "-c", "JAVA_OPTIONS" -jar /root/Java_Architect_Spring-0.0.1-SNAPSHOT.jar ]