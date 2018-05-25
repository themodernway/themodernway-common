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
import com.themodernway.common.api.java.util.StringOps;

public abstract class AbstractModuleBase<R, T extends AbstractModuleBase<R, T>> extends Activatable implements IModule
{
    private final String          m_name;

    private final String          m_vers;

    private final ICallable<R, T> m_exec;

    private final AtomicBoolean   m_open = new AtomicBoolean(true);

    protected AbstractModuleBase(final String name, final String vers)
    {
        this(name, vers, true, CommonOps.NULL());
    }

    protected AbstractModuleBase(final String name, final String vers, final boolean active)
    {
        this(name, vers, active, CommonOps.NULL());
    }

    protected AbstractModuleBase(final String name, final String vers, final ICallable<R, T> exec)
    {
        this(name, vers, true, exec);
    }

    protected AbstractModuleBase(final String name, final String vers, final boolean active, final ICallable<R, T> exec)
    {
        super(active);

        m_name = CommonOps.requireNonNull(name, "module name is null.");

        m_vers = CommonOps.requireNonNull(vers, "module vers is null.");

        m_exec = exec;
    }

    protected ICallable<R, T> getCallable()
    {
        return m_exec;
    }

    @Override
    public void refresh(final Object... args)
    {
        final ICallable<R, T> exec = getCallable();

        if ((null != exec) && isOpen() && isActive())
        {
            try
            {
                exec.call(CommonOps.CAST(this), args);
            }
            catch (final Exception e)
            {
                throw new FrameworkException(e);
            }
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
