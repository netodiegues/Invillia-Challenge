FROM java:8
VOLUME /tmp
ADD invillia-Store.jar service-store.jar
RUN sh -c 'touch /service-store.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx128m", "-jar", "/service-store.jar"]