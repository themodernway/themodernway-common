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

package com.themodernway.common.api.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FixedListIterable<T> implements IFixedIterable<T>
{
    private final List<T> m_list;

    public FixedListIterable(final List<T> list)
    {
        m_list = new ArrayList<T>(Objects.requireNonNull(list));
    }

    @SafeVarargs
    public FixedListIterable(final T... list)
    {
        this(Arrays.asList(Objects.requireNonNull(list)));
    }

    @Override
    public String toString()
    {
        return m_list.toString();
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

    @Override
    public Stream<T> stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }
}
