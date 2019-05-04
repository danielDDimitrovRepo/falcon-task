package com.falcon.consumer;

import com.falcon.model.Falcon;
import com.google.gson.stream.MalformedJsonException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.ERROR;
import static org.apache.camel.LoggingLevel.INFO;

/**
 * This class is a Camel Route that acts as a pipeline for processing Kafka messages
 */
@Component
public class FalconConsumerRoute extends RouteBuilder {

    @Override
    public void configure() {
        onException(MalformedJsonException.class)
                .log(ERROR, "${exception.message}")
                .handled(true);

        from("{{app.camel.kafka.component}}")
                .log(INFO, "Received message: ${body}")
                .unmarshal().json(JsonLibrary.Gson, Falcon.class)
                .to("jpa://com.falcon.model.Falcon")
                .log(INFO, "Processed message: ${body}");
    }

}