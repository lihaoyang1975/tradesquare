FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

ADD build/libs/tradesquare-0.0.1-SNAPSHOT.jar /tradesquare.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/tradesquare.jar" ]