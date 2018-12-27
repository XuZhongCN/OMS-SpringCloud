FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /usr/test
ADD oms-eureka-0.0.1-SNAPSHOT.jar oms-eureka-0.0.1-SNAPSHOT.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/oms-eureka-0.0.1-SNAPSHOT.jar"]
EXPOSE 8001
