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
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import com.themodernway.common.api.java.util.CommonOps;

public class CoreCursor<T> implements ICursor<T>
{
    private final Iterator<T>   m_iter;

    private final AtomicBoolean m_open = new AtomicBoolean(true);

    private final AtomicBoolean m_auto = new AtomicBoolean(true);

    public CoreCursor(final Iterator<T> srciter)
    {
        m_iter = CommonOps.requireNonNull(srciter);
    }

    @SafeVarargs
    public CoreCursor(final T... source)
    {
        this(CommonOps.toList(source).iterator());
    }

    public CoreCursor(final Stream<T> source)
    {
        this(CommonOps.toList(source).iterator());
    }

    public CoreCursor(final Collection<T> source)
    {
        this(CommonOps.toList(source).iterator());
    }

    public CoreCursor(final Enumeration<T> source)
    {
        this(CommonOps.toList(source).iterator());
    }

    public CoreCursor(final IFixedIterable<T> source)
    {
        this(CommonOps.toList(source).iterator());
    }

    @Override
    public Iterator<T> self()
    {
        return m_iter;
    }

    @Override
    public void close() throws IOException
    {
        m_open.set(false);
    }

    @Override
    public boolean hasNext()
    {
        final boolean next = (isOpen() && self().hasNext());

        if ((false == next) && (isAutoClosed() && isOpen()))
        {
            try
            {
                close();
            }
            catch (final IOException e)
            {
                onFailure(e);
            }
        }
        return next;
    }

    @Override
    public T next()
    {
        return self().next();
    }

    @Override
    public boolean isOpen()
    {
        return m_open.get();
    }

    @Override
    public boolean isAutoClosed()
    {
        return m_auto.get();
    }

    @Override
    public boolean setAutoClosed(final boolean auto)
    {
        return (m_auto.getAndSet(auto) != auto);
    }
}
