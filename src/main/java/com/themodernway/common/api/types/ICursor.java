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
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.themodernway.common.api.java.util.CommonOps;

public interface ICursor<T> extends Iterator<T>, IFailable, IAutoCloseable, Serializable
{
    default <A extends Collection<? super T>> A into(final A target)
    {
        CommonOps.requireNonNull(target);

        if (isOpen())
        {
            forEachRemaining(target::add);
        }
        return target;
    }

    default Iterator<T> self()
    {
        return this;
    }

    @Override
    default void forEachRemaining(final Consumer<? super T> action)
    {
        CommonOps.requireNonNull(action);

        if (isOpen())
        {
            while (hasNext())
            {
                action.accept(next());
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
    }

    @Override
    default void onFailure(final Throwable throwable)
    {
        // don't do anything for the default case
    }

    @Override
    default void close() throws IOException
    {
        // don't do anything for the default case
    }

    default Spliterator<T> spliterator()
    {
        return Spliterators.spliteratorUnknownSize(this, 0);
    }

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
