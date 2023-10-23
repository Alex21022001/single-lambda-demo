package com.alex.sitiy.event;

public record Lifecycle(Tf tf) implements LambdaEvent {

  public record Tf(String action) {
  }

  public enum Action {
    create, update, delete
  }

  @Override
  public String toString() {
    return "Lifecycle:" + tf.action;
  }

}
