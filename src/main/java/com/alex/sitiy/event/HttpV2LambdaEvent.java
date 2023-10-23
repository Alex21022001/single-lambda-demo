package com.alex.sitiy.event;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;

public class HttpV2LambdaEvent extends APIGatewayV2HTTPEvent implements LambdaEvent {
}
