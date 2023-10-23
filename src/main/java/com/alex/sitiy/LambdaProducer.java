package com.alex.sitiy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class LambdaProducer {

    @Produces
    @ApplicationScoped
    public LambdaProvider wsLambdaProvider(){
        return new WsHandler();
    }

    @Produces
    @ApplicationScoped
    public LambdaProvider tgLambdaProvider(){
        return new TgHandler();
    }

    @Produces
    @ApplicationScoped
    public LambdaProvider lifecycleLambdaProvider(){
        return new LifecycleHandler();
    }
}
