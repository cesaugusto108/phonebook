FROM bellsoft/liberica-openjdk-debian
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV PORT=server_port
ENV DB_HOST=host
ENV DB_PORT=db_port
ENV USERNAME=username
ENV PASSWORD=password
ENV PROFILE=default
ENTRYPOINT ["java", "-DPORT=${PORT}", "-DDB-HOST=${DB_HOST}", "-DDB-PORT=${DB_PORT}", "-DUSERNAME=${USERNAME}", "-DPASSWORD=${PASSWORD}", "-DPROFILE=${PROFILE}", "-jar", "/app.jar"]