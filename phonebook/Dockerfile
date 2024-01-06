FROM bellsoft/liberica-openjdk-debian
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SERVERPORT=server_port
ENV HOST=host
ENV DBPORT=db_port
ENV USER=username
ENV PASS=password
ENV PROFILE=default
ENTRYPOINT ["java", \
            "-DSERVER-PORT=${SERVERPORT}", \
            "-DDB-HOST=${HOST}", \
            "-DDB-PORT=${DBPORT}", \
            "-DUSERNAME=${USER}", \
            "-DPASSWORD=${PASS}", \
            "-DACTIVE_PROFILE=${PROFILE}", \
            "-jar", \
            "/app.jar"]