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
import java.util.function.Consumer;

import com.themodernway.common.api.java.util.CommonOps;
import com.themodernway.common.api.java.util.StringOps;

public abstract class AbstractModule<T extends AbstractModule<T>> implements IModule
{
    private final String        m_name;

    private final String        m_vers;

    private final Consumer<T>   m_exec;

    private final AtomicBoolean m_actv;

    private final AtomicBoolean m_open = new AtomicBoolean(true);

    protected AbstractModule(final String name, final String vers)
    {
        this(name, vers, true, null);
    }

    protected AbstractModule(final String name, final String vers, final boolean active)
    {
        this(name, vers, active, null);
    }

    protected AbstractModule(final String name, final String vers, final Consumer<T> exec)
    {
        this(name, vers, true, exec);
    }

    protected AbstractModule(final String name, final String vers, final boolean active, final Consumer<T> exec)
    {
        m_name = CommonOps.requireNonNull(name, () -> "module name is null.");

        m_vers = CommonOps.requireNonNull(vers, () -> "module vers is null.");

        m_actv = new AtomicBoolean(active);

        m_exec = exec;
    }

    @Override
    public void refresh()
    {
        if ((null != m_exec) && isOpen() && isActive())
        {
            m_exec.accept(CommonOps.CAST(this));
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
    public boolean isActive()
    {
        return m_actv.get();
    }

    @Override
    public boolean setActive(final boolean active)
    {
        return (m_actv.getAndSet(active) != active);
    }

    @Override
    public boolean isOpen()
    {
        return m_open.get();
    }

    @Override
    public void close() throws IOException
    {
        if (m_open.getAndSet(false) && isActive())
        {
            setActive(false);
        }
    }

    @Override
    public String toString()
    {
        return StringOps.toPrintableString(getName(), getVersion(), Boolean.toString(isActive()), Boolean.toString(isOpen()));
    }
}
