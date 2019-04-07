FROM java:8
VOLUME /tmp
ADD invillia-Order.jar service-order.jar
RUN sh -c 'touch /service-order.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xmx128m", "-jar", "/service-order.jar"]