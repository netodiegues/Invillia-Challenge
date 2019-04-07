FROM java:8
VOLUME /tmp
ADD invillia-Security.jar service-security.jar
RUN sh -c 'touch /service-security.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx128m", "-jar", "/service-security.jar"]