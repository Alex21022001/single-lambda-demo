package com.alex.sitiy;

import com.alex.sitiy.event.LambdaEvent;
import com.alex.sitiy.event.Lifecycle;
import com.alex.sitiy.event.Lifecycle.Action;
import com.amazonaws.services.lambda.runtime.Context;
import io.quarkus.logging.Log;

public class LifecycleHandler implements LambdaProvider<Lifecycle, String> {

  @Override
  public String handleRequest(Lifecycle input, Context context) {
    Action action = Action.valueOf(input.tf().action());
    var result = switch (action) {
      case create -> registerWebhook();
      case update -> registerWebhook();
      case delete -> unregisterWebhook();
    };
    Log.infof("Lifecycle action %s -> %s", action, result);
    return result;
  }

  private String registerWebhook() {
    return "Registered telegram webhook " ;
  }

  private String unregisterWebhook() {
    return "Unregistered telegram webhook";
  }

  @Override
  public boolean support(LambdaEvent event) {
    return event.getClass() == Lifecycle.class;
  }
}
