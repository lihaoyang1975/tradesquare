FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

ADD build/libs/tradesquare-0.0.1-SNAPSHOT.jar /tradesquare.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT [ "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/tradesquare.jar" ]