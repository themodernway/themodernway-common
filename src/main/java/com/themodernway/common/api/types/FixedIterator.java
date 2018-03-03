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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import com.themodernway.common.api.java.util.CommonOps;

public class FixedIterator<T> implements Iterator<T>
{
    private int                     m_posn;

    private final int               m_size;

    private final IFixedIterable<T> m_iter;

    public FixedIterator(final IFixedIterable<T> iter)
    {
        m_iter = iter;

        m_size = m_iter.size();
    }

    @Override
    public boolean hasNext()
    {
        return (m_posn != m_size);
    }

    @Override
    public T next()
    {
        final int i = m_posn;

        if (i >= m_size)
        {
            throw new NoSuchElementException();
        }
        m_posn = i + 1;

        return m_iter.get(i);
    }

    @Override
    public void forEachRemaining(final Consumer<? super T> action)
    {
        CommonOps.requireNonNull(action);

        while (hasNext())
        {
            action.accept(next());
        }
    }
}
