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

import java.io.Serializable;

import net.jcip.annotations.Immutable;

@Immutable
public class Greeting implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3250733488466794459L;

    private final String who;

    public Greeting(final String who) {
        this.who = who;
    }

    public String getWho() {
        return who;
    }
}
