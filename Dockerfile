FROM tomcat:9.0.40-jdk15-openjdk

COPY ./Java_Architect_Spring/WebContent/META-INF/Java_Architect_Spring.war /usr/local/tomcat/webapps/Java_Architect_Spring.war

CMD [ "catalina.sh", "run" ]