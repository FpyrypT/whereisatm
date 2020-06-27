FROM openjdk:11
VOLUME /tmp
COPY target/whereisatm-0.0.1-SNAPSHOT.jar whereisatm-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","whereisatm-0.0.1-SNAPSHOT.jar"]