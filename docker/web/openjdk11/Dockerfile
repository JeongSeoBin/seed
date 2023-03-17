FROM openjdk:11
COPY ./*.jar /home/web/web.jar

RUN apt-get update && apt-get install -y iputils-ping

ENV JAVA_OPTS=''

ENTRYPOINT java ${JAVA_OPTS} -jar /home/web/web.jar