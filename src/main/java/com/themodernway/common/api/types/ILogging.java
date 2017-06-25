/*
   Copyright (c) 2017, The Modern Way. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.themodernway.common.api.types;

import java.util.function.Supplier;

public interface ILogging
{
    public boolean isTraceOn();

    public boolean isDebugOn();

    public boolean isInfoOn();

    public boolean isWarnOn();

    public boolean isErrorOn();

    public boolean isSevereOn();

    public boolean isFatalOn();

    public void trace(String message);

    public void trace(String message, Throwable e);

    public void debug(String message);

    public void debug(String message, Throwable e);

    public void info(String message);

    public void info(String message, Throwable e);

    public void warn(String message);

    public void warn(String message, Throwable e);

    public void error(String message);

    public void error(String message, Throwable e);

    public void severe(String message);

    public void severe(String message, Throwable e);

    public void fatal(String message);

    public void fatal(String message, Throwable e);

    default public void trace(Supplier<String> message)
    {
        if (isTraceOn())
        {
            trace(message.get());
        }
    }

    default public void trace(Supplier<String> message, Throwable e)
    {
        if (isTraceOn())
        {
            trace(message.get(), e);
        }
    }

    default public void debug(Supplier<String> message)
    {
        if (isDebugOn())
        {
            debug(message.get());
        }
    }

    default public void debug(Supplier<String> message, Throwable e)
    {
        if (isDebugOn())
        {
            debug(message.get(), e);
        }
    }

    default public void info(Supplier<String> message)
    {
        if (isInfoOn())
        {
            info(message.get());
        }
    }

    default public void info(Supplier<String> message, Throwable e)
    {
        if (isInfoOn())
        {
            info(message.get(), e);
        }
    }

    default public void warn(Supplier<String> message)
    {
        if (isWarnOn())
        {
            warn(message.get());
        }
    }

    default public void warn(Supplier<String> message, Throwable e)
    {
        if (isWarnOn())
        {
            warn(message.get(), e);
        }
    }

    default public void error(Supplier<String> message)
    {
        if (isErrorOn())
        {
            error(message.get());
        }
    }

    default public void error(Supplier<String> message, Throwable e)
    {
        if (isErrorOn())
        {
            error(message.get(), e);
        }
    }

    default public void severe(Supplier<String> message)
    {
        if (isSevereOn())
        {
            severe(message.get());
        }
    }

    default public void severe(Supplier<String> message, Throwable e)
    {
        if (isSevereOn())
        {
            severe(message.get(), e);
        }
    }

    default public void fatal(Supplier<String> message)
    {
        if (isFatalOn())
        {
            fatal(message.get());
        }
    }

    default public void fatal(Supplier<String> message, Throwable e)
    {
        if (isFatalOn())
        {
            fatal(message.get(), e);
        }
    }
}