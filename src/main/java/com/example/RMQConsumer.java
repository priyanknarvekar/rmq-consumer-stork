package com.example;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.stork.Stork;
import io.smallrye.stork.api.Service;
import io.smallrye.stork.api.ServiceInstance;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class RMQConsumer {

    @Blocking
    @Incoming("from-rabbitmq")
    public void consume(JsonObject p) {
        
        Stork stork = Stork.getInstance();
        //Stork.initialize();

        Service service = stork.getService("proxy");
        ServiceInstance instance = service.selectInstance()
                .await().atMost(Duration.ofSeconds(5));
        Log.infof("service instance : %s",instance.getHost());


        Price price = p.mapTo(Price.class);
        Log.infof("price = %d",price.getValue());
    }

}