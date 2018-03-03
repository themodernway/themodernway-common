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
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.themodernway.common.api.java.util.CommonOps;

public interface IFixedIterable<T> extends Iterable<T>
{
    public int size();

    public boolean isEmpty();

    public T get(int index);

    @Override
    default Iterator<T> iterator()
    {
        return new FixedIterator<T>(this);
    }

    @Override
    default Spliterator<T> spliterator()
    {
        return Spliterators.spliterator(iterator(), size(), Spliterator.SIZED | Spliterator.ORDERED | Spliterator.IMMUTABLE);
    }

    default Stream<T> stream()
    {
        return StreamSupport.stream(spliterator(), false);
    }

    default <A extends Collection<? super T>> A into(final A target)
    {
        CommonOps.requireNonNull(target);

        forEach(target::add);

        return target;
    }
}
