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

package com.themodernway.common.api.types;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.themodernway.common.api.java.util.CommonOps;

public class Module implements IModule
{
    private final String        m_name;

    private final String        m_vers;

    private final Runnable      m_exec;

    private final AtomicBoolean m_open = new AtomicBoolean(true);

    public Module(final String name, final String vers)
    {
        this(name, vers, NO_RUNNABLE);
    }

    public Module(final String name, final String vers, final Runnable exec)
    {
        m_name = CommonOps.requireNonNull(name, () -> "module name is null.");

        m_vers = CommonOps.requireNonNull(vers, () -> "module vers is null.");

        m_exec = exec;
    }

    @Override
    public void refresh()
    {
        if (null != m_exec)
        {
            m_exec.run();
        }
    }

    @Override
    public String getName()
    {
        return m_name;
    }

    @Override
    public String getVersion()
    {
        return m_vers;
    }

    @Override
    public boolean isOpen()
    {
        return m_open.get();
    }

    @Override
    public void close() throws IOException
    {
        m_open.set(false);
    }
}
