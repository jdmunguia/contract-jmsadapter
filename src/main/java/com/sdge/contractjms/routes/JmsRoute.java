package com.sdge.contractjms.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by ddmungui on 3/30/2017.
 */
@Component
public class JmsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("{{inbound.endpoint}}")
                .transacted()
                .log(LoggingLevel.INFO, log, "Recieved messages ")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        log.info("Exchange: {}",exchange);
                    }
                })
                .loop()
                .simple("{{outbound.loop.count}}")
                .to("{{outbound.endpoint}}")
                .log(LoggingLevel.INFO,log,"Message Sent. Iteraction ${property.CamelLoopIndex}")
                .end();
    }
}
