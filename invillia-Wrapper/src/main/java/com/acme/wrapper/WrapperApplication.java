package com.acme.wrapper;

import com.acme.wrapper.infra.wiremock.WiremockStarter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class WrapperApplication {

    public static void main(String args[]) {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "prod");
        SpringApplication.run(WrapperApplication.class);
    }

    @PostConstruct
    public void onStart() {        
        WiremockStarter.getInstance().StartOnce();
    }

    @PreDestroy
    public void onExit() {
        WiremockStarter.getInstance().stop();
    }
}
