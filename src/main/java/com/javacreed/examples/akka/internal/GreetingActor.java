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

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;

@Component("greetingActor")
@Scope("prototype")
public class GreetingActor extends UntypedActor {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetingActor.class);

  private final GreetingService greetingService;

  @Autowired
  public GreetingActor(final GreetingService greetingService) {
    this.greetingService = Objects.requireNonNull(greetingService);
  }

  @Override
  public void onReceive(final Object message) throws Exception {
    GreetingActor.LOGGER.debug("Recevied message: {}", message);
    if (message instanceof Subject) {
      greetingService.greet((Subject) message);
    }
  }

}
