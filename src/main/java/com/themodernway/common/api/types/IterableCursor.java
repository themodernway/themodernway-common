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
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.themodernway.common.api.java.util.CommonOps;

public interface IterableCursor<T> extends Iterable<T>, ICursor<T>
{
    @Override
    default <A extends Collection<? super T>> A into(final A target)
    {
        CommonOps.requireNonNull(target);

        if (isOpen())
        {
            forEach(target::add);
        }
        return target;
    }

    @Override
    default Iterator<T> iterator()
    {
        return this;
    }

    @Override
    default void forEach(final Consumer<? super T> action)
    {
        CommonOps.requireNonNull(action);

        for (final T t : this)
        {
            action.accept(t);
        }
        if (isAutoClosed() && isOpen())
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
    }

    @Override
    default Spliterator<T> spliterator()
    {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    @Override
    default Stream<T> stream()
    {
        if (isOpen())
        {
            return StreamSupport.stream(spliterator(), false).onClose(() -> {

                if (isAutoClosed() && isOpen())
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
            });
        }
        return Stream.empty();
    }
}
