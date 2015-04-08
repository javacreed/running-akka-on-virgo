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
import org.springframework.context.ApplicationContext;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;

/**
 * An Akka Extension to provide access to Spring managed Actor Beans.
 * 
 * Based On:
 * https://github.com/typesafehub/activator-akka-java-spring/blob/master/src/main/java/sample/SpringExtension.java
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

  /**
   * The Extension implementation.
   */
  public static class SpringExt implements Extension {

    private volatile ApplicationContext applicationContext;

    /**
     * Used to initialize the Spring application context for the extension.
     * 
     * @param applicationContext
     */
    public void initialize(final ApplicationContext applicationContext) {
      this.applicationContext = Objects.requireNonNull(applicationContext);
    }

    /**
     * Create a Props for the specified actorBeanName using the SpringActorProducer class.
     * 
     * @param actorBeanName
     *          The name of the actor bean to create Props for
     * @return a Props that will create the named actor bean using Spring
     */
    public Props props(final String actorBeanName) {
      SpringExtension.LOGGER.debug("Creating props for {} (using {})", actorBeanName, applicationContext);
      return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(SpringExtension.class);

  /**
   * The identifier used to access the SpringExtension.
   */
  public static final SpringExtension SpringExtProvider = new SpringExtension();

  /**
   * Is used by Akka to instantiate the Extension identified by this ExtensionId, internal use only.
   */
  @Override
  public SpringExt createExtension(final ExtendedActorSystem system) {
    return new SpringExt();
  }
}
