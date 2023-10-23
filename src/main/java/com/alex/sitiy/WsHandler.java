package com.alex.sitiy;

import com.alex.sitiy.event.LambdaEvent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2WebSocketResponse;
import io.quarkus.logging.Log;

public class WsHandler implements LambdaProvider<APIGatewayV2WebSocketEvent, APIGatewayV2WebSocketResponse> {

    @Override
    public boolean support(LambdaEvent event) {
        return event.getClass().getSuperclass() == APIGatewayV2WebSocketEvent.class;
    }

    @Override
    public APIGatewayV2WebSocketResponse handleRequest(APIGatewayV2WebSocketEvent input, Context context) {
        Log.info("Handle Request");
        Log.info(input);

        final var response = new APIGatewayV2WebSocketResponse();
        response.setStatusCode(200);
        Log.debugf("ws %s (%s) -> %s", input, context, response);
        return response;
    }
}
