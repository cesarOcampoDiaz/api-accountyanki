package com.nttdata.router;


import com.nttdata.handler.AccountYankiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class AccountYankiRouter {
    @Bean
    public RouterFunction<ServerResponse> accountYankiRouterFunc(AccountYankiHandler transactionHandler) {
        return RouterFunctions.route(POST("/accountYanki").and(accept(MediaType.APPLICATION_JSON)), transactionHandler::add)
                .andRoute(GET("/accountYanki").and(accept(MediaType.TEXT_EVENT_STREAM)), transactionHandler::findAll)
                .andRoute(GET("/accountYanki/{id}").and(accept(MediaType.APPLICATION_JSON)), transactionHandler::findById)
                .andRoute(PUT("/accountYanki/{id}").and(accept(MediaType.APPLICATION_JSON)), transactionHandler::update)
                .andRoute(GET("/accountYanki/sald/trans").and(accept(MediaType.APPLICATION_JSON)),transactionHandler::balance);


    }

}
