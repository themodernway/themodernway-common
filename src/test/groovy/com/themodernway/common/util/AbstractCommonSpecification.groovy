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

import static java.lang.System.err

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.themodernway.common.api.types.json.JSONStringify

import spock.lang.Specification

public abstract class AbstractCommonSpecification extends Specification
{
    private Logger      m_logger

    private boolean     m_logging = true

    public static final void setupCommonDefault()
    {
    }

    public static final void closeCommonDefault()
    {
    }

    def setup()
    {
        m_logging = true
    }

    public Logger logger()
    {
        if (null == m_logger)
        {
            m_logger = LoggerFactory.getLogger(getClass())
        }
        m_logger
    }

    public void logging(boolean on = true)
    {
        m_logging = on
    }

    public void echo(JSONStringify o)
    {
        if (m_logging)
        {
            logger().info("" + o?.toJSONString())
        }
        else
        {
            println "" + o?.toJSONString()
        }
    }

    public void echo(def o)
    {
        if (m_logging)
        {
            logger().info("" + o?.toString())
        }
        else
        {
            println "" + o?.toString()
        }
    }

    public void oops(JSONStringify o)
    {
        if (m_logging)
        {
            logger().error("" + o?.toJSONString())
        }
        else
        {
            err.println "" + o?.toJSONString()
        }
    }

    public void oops(def o)
    {
        if (m_logging)
        {
            logger().error("" + o?.toString())
        }
        else
        {
            err.println "" + o?.toString()
        }
    }
}
