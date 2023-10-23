package com.alex.sitiy.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(value = {
        @Type(WebSocketLambdaEvent.class),
        @Type(HttpV2LambdaEvent.class),
        @Type(Lifecycle.class)
})
public interface LambdaEvent {
}
