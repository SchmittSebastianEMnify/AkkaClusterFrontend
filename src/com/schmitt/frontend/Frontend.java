package com.schmitt.frontend;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import akka.actor.Props;



public class Frontend {

  public static void main(String[] args) {
    if (args.length == 0)
      startup(new String[] {"2551", "2552", "0"});
    else
      startup(args);
  }

  public static void startup(String[] ports) {

    // Override the configuration of the port
    Config config =
        ConfigFactory.parseString("akka.remote.netty.tcp.port=" + ports[2]).withFallback(
            ConfigFactory.load("frontend"));

    // Create an Akka system

    ActorSystem system = ActorSystem.create("ClusterSystem", config);
    
    // Create an actor that handles cluster domain events
    system.actorOf(Props.create(FrontendActor.class), "FrontendActor");
  }
}
