package com.acme.wrapper.infra.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jose.diegues
 */
public final class WiremockStarter {

    private static final Integer WIREMOCK_PORT = 10000;
    private volatile WireMockServer wireMockServer;
    private static WiremockStarter instance;
    private static final Logger log = LoggerFactory.getLogger(WiremockStarter.class);

    public WiremockStarter() {
    }

    public static synchronized WiremockStarter getInstance() {
        if (instance == null) {
            instance = new WiremockStarter();
        }
        return instance;
    }

    public synchronized void StartOnce() {
        if (wireMockServer == null) {
            log.info("Creating WireMock Server on port: {},", WIREMOCK_PORT);

            wireMockServer = new WireMockServer(WireMockConfiguration
                    .wireMockConfig()                    
                    .port(WIREMOCK_PORT)
            );

            log.info("Initializing WireMock Server");

            wireMockServer.start();
            log.info("WireMock Server successfully initialized");
        }
    }

    public synchronized void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
            log.info("Finalizing WireMock Server");
        }
    }
}
