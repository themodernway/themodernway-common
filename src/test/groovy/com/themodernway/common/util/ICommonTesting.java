/*
 * Copyright (c) 2018, The Modern Way. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.themodernway.common.util;

import java.net.URL;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public interface ICommonTesting
{
    public static class TestingOps
    {
        public static final void setupCommonLogging() throws Exception
        {
            setupCommonLogging("logback-testing.xml");
        }

        public static final void setupCommonLogging(final String location) throws Exception
        {
            final URL url = TestingOps.class.getResource(location);

            if (null == url)
            {
                System.err.println("can't find " + location);
            }
            else
            {
                final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

                try
                {
                    final JoranConfigurator configurator = new JoranConfigurator();

                    configurator.setContext(context);

                    context.reset();

                    configurator.doConfigure(url);
                }
                catch (final JoranException e)
                {
                    StatusPrinter.printInCaseOfErrorsOrWarnings(context);

                    return;
                }
                StatusPrinter.printInCaseOfErrorsOrWarnings(context);
            }
        }

        public static final void setupCommonDefault() throws Exception
        {
            setupCommonLogging();
        }

        public static final void closeCommonLogging()
        {
            final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

            context.stop();
        }

        public static final void closeCommonDefault()
        {
            closeCommonLogging();
        }

        protected TestingOps()
        {
        }
    }
}
