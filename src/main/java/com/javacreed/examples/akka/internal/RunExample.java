/*
 * #%L
 * Running AKKA on Virgo
 * %%
 * Copyright (C) 2012 - 2015 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.javacreed.examples.akka.internal;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Component
public class RunExample {

  private static final Logger LOGGER = LoggerFactory.getLogger(RunExample.class);

  @Autowired
  private ActorSystem system;

  @PostConstruct
  public void run() {
    try {
      RunExample.LOGGER.debug("Running example");
      final ActorRef greeter = system.actorOf(SpringExtension.SpringExtProvider.get(system).props("greetingActor"),
          "greeter");

      RunExample.LOGGER.debug("Sending message");
      greeter.tell(new Subject("Albert Attard"), greeter);
      RunExample.LOGGER.debug("Message sent");
    } catch (final Throwable e) {
      RunExample.LOGGER.error("Failed to run example", e);
    } finally {
      RunExample.LOGGER.debug("Done");
    }
  }
}
