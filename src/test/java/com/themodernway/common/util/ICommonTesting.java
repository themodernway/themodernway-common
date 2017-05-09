/*
 * Copyright (c) 2017, The Modern Way. All rights reserved.
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

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public interface ICommonTesting
{
    public static class TestingOps
    {
        public static final void setupCommonLogging() throws Exception
        {
            setupCommonLogging("testing-log4j.xml");
        }

        public static final void setupCommonLogging(final String location) throws Exception
        {
            final URL url = TestingOps.class.getResource(location);

            if (location.toLowerCase().endsWith(".xml"))
            {
                DOMConfigurator.configure(url);
            }
            else
            {
                PropertyConfigurator.configure(url);
            }
        }

        public static final void setupCommonDefault() throws Exception
        {
            setupCommonLogging();
        }

        public static final void closeCommonLogging()
        {
            LogManager.shutdown();
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
