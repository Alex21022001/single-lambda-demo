package com.alex.sitiy;

import com.alex.sitiy.event.LambdaEvent;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public interface LambdaProvider<I,O> extends RequestHandler<I,O> {
    boolean support(LambdaEvent event);
}
