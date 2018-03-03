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

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;

import com.themodernway.common.api.java.util.CommonOps;

public class FixedListIterable<T> implements IFixedIterable<T>
{
    private final List<T> m_list;

    @SafeVarargs
    public FixedListIterable(final T... source)
    {
        m_list = CommonOps.toList(source);
    }

    public FixedListIterable(final ICursor<T> source)
    {
        m_list = CommonOps.toList(source);
    }

    public FixedListIterable(final Stream<T> source)
    {
        m_list = CommonOps.toList(source);
    }

    public FixedListIterable(final Collection<T> source)
    {
        m_list = CommonOps.toList(source);
    }

    public FixedListIterable(final Enumeration<T> source)
    {
        m_list = CommonOps.toList(source);
    }

    @Override
    public int size()
    {
        return m_list.size();
    }

    @Override
    public boolean isEmpty()
    {
        return m_list.isEmpty();
    }

    @Override
    public T get(final int index)
    {
        return m_list.get(index);
    }

    public List<T> asList()
    {
        return CommonOps.toUnmodifiableList(m_list);
    }

    @Override
    public String toString()
    {
        return m_list.toString();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if (other instanceof FixedListIterable)
        {
            final FixedListIterable<T> fixed = CommonOps.cast(other);

            return m_list.equals(fixed.m_list);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return m_list.hashCode();
    }
}
