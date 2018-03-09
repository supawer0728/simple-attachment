package com.parfiat.study.simpleattachment.webflux.config;

import com.parfiat.study.simpleattachment.webflux.board.BoardHandler;
import com.parfiat.study.simpleattachment.webflux.config.filter.AttachmentHandlerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@EnableWebFlux
@Configuration
public class WebConfig extends DelegatingWebFluxConfiguration {

    @Autowired
    private BoardHandler boardHandler;
    private AttachmentHandlerFilter attachmentFilter = new AttachmentHandlerFilter();

    @Bean
    public RouterFunction<?> boardRouterFunctions() {
        return route(GET("/boards/{id}").and(accept(APPLICATION_JSON)), boardHandler::getBoard).filter(attachmentFilter.filter());
    }
}