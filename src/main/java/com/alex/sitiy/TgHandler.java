package com.alex.sitiy;

import com.alex.sitiy.event.LambdaEvent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import io.quarkus.logging.Log;

import java.util.Map;

public class TgHandler implements LambdaProvider<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse>{

    @Override
    public boolean support(LambdaEvent event) {
        return event.getClass().getSuperclass() == APIGatewayV2HTTPEvent.class;
    }

    @Override
    public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent input, Context context) {
        Log.info("Handle Request");
        Log.info(input);

        return APIGatewayV2HTTPResponse
                .builder()
                .withStatusCode(200)
                .withHeaders(Map.of("Content-Type", "application/json"))
                .withBody("Success")
                .build();
    }
}
