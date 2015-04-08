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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("fileGreetingService")
public class FileGreetingService implements GreetingService {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileGreetingService.class);

  @Override
  public void greet(final Subject subject) {
    FileGreetingService.LOGGER.debug("Greeting: {}", subject);
    try (PrintStream out = new PrintStream(new FileOutputStream("greetings.txt", true), true, "UTF-8")) {
      out.printf("[%tF %<tT.%<tL] Hello %s%n", System.currentTimeMillis(), subject.getName());
    } catch (final IOException e) {
      FileGreetingService.LOGGER.error("Failed to greet: {}", subject, e);
    }
  }

}
