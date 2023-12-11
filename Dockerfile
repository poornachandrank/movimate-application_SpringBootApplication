FROM openjdk:8
EXPOSE 8081
COPY target/movie-Application-0.0.1-SNAPSHOT.war  /movie-Application-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java" ,"-war","/movie-Application-0.0.1-SNAPSHOT.war"]
