FROM java:8
VOLUME /tmp
ADD invillia-Billing.jar service-Billing.jar
RUN sh -c 'touch /service-Billing.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx128m", "-jar", "/service-Billing.jar"]