FROM openjdk:11

ADD target/twitter-service-*-SNAPSHOT.jar /twitter-service.jar
ADD config.yml config.yml

#RUN java -jar twitter-service.jar db migrate /config.yml
CMD java\
 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9091\
 -Ddw.server.applicationConnectors[0].port=9090\
 -jar\
 twitter-service.jar server config.yml

EXPOSE 9090
EXPOSE 9091