package com.schmitt.frontend;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.ArrayList;
import java.util.List;

public class FrontendActor extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
  List<ActorRef> backends = new ArrayList<ActorRef>();

  @Override
  public void onReceive(Object message) throws Exception {
    if (message instanceof String) {
      String messageString = (String) message;
      switch (messageString) {
        case "Welcome":
          getSender().tell("Mahlzeit", getSelf());
          log.error(messageString);
          break;
        case "Bye":
          getSender().tell("Ciao", getSelf());
          log.error(messageString);
          break;
        default:
          break;
      }
    } else {
      unhandled(message);
    }
  }

}
