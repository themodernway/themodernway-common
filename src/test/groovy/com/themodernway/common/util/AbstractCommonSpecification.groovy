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

package com.themodernway.common.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import groovy.transform.CompileStatic
import spock.lang.Specification

@CompileStatic
public abstract class AbstractCommonSpecification extends Specification
{
    private Logger      m_logger = LoggerFactory.getLogger(getClass())

    private boolean     m_logging = true

    def setup()
    {
        m_logging = true
    }

    def Logger logger()
    {
        m_logger
    }

    def logging(boolean on = true)
    {
        m_logging = on
    }

    def echo(def o)
    {
        if (m_logging)
        {
            logger().info("" + o?.toString())
        }
        else
        {
            println("" + o?.toString())
        }
    }

    def oops(def o)
    {
        if (m_logging)
        {
            logger().error("" + o?.toString())
        }
        else
        {
            System.err.println("" + o?.toString())
        }
    }
}
