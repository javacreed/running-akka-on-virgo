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

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

/**
 * An actor producer that lets Spring create the Actor instances.
 * 
 * Based on:
 * https://github.com/typesafehub/activator-akka-java-spring/blob/master/src/main/java/sample/SpringActorProducer.java
 */
public class SpringActorProducer implements IndirectActorProducer {

  /** The spring application context (which will not be {@code null}) */
  private final ApplicationContext applicationContext;

  /** The actor bean name (which will not be {@code null}) */
  private final String actorBeanName;

  /**
   * 
   * @param applicationContext
   *          the spring application context (which will not be {@code null})
   * @param actorBeanName
   *          the actor bean name (which will not be {@code null})
   * @throws NullPointerException
   *           if any of the parameters is {@code null}
   */
  public SpringActorProducer(final ApplicationContext applicationContext, final String actorBeanName)
      throws NullPointerException {
    this.applicationContext = Objects.requireNonNull(applicationContext);
    this.actorBeanName = Objects.requireNonNull(actorBeanName);
  }

  @Override
  public Class<? extends Actor> actorClass() {
    @SuppressWarnings("unchecked")
    final Class<? extends Actor> t = (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    return t;
  }

  @Override
  public Actor produce() {
    return applicationContext.getBean(actorBeanName, Actor.class);
  }

}
