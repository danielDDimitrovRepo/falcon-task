package com.falcon.producer;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.Exchange.HTTP_RESPONSE_CODE;
import static org.apache.camel.LoggingLevel.ERROR;
import static org.apache.camel.LoggingLevel.INFO;
import static org.apache.camel.model.rest.RestBindingMode.json;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This class is a Camel route that exposes a "REST-to-Kafka" pipeline as a standard callable POST endpoint
 */
@Component
public class FalconProducerRoute extends RouteBuilder {

    @Override
    public void configure() {
        onException(JsonParseException.class)
                .log(ERROR, "${exception.message}")
                .handled(true)
                .setHeader(HTTP_RESPONSE_CODE, constant(BAD_REQUEST.value()))
                .setHeader(CONTENT_TYPE, constant(APPLICATION_JSON_VALUE))
                .setBody().constant("Invalid JSON request");

        rest("/producer")
                .post("/send-falcon-message")
                .consumes(APPLICATION_JSON_VALUE)
                .produces(APPLICATION_JSON_VALUE)
                .bindingMode(json)
                .to("direct:{{app.camel.direct.validate-falcon}}");

        from("direct:{{app.camel.direct.validate-falcon}}")
                .log(INFO, "Processing message: ${body}")
                .process(exchange -> {
                    if (isEmpty(ofNullable((String) exchange.getIn().getBody(Map.class).get("message"))
                            .orElseThrow(() -> new JsonParseException(null, "JSON property not found: 'message' "))))
                        throw new JsonParseException(null, "JSON property is empty: 'message' ");
                })
                .to("seda:{{app.camel.kafka.async.route}}?waitForTaskToComplete=Never");

        from("seda:{{app.camel.kafka.async.route}}")
                .to("{{app.camel.kafka.component}}")
                .log(INFO, "Message sent: ${body}");
    }

}