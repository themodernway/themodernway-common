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

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.themodernway.common.api.java.util.CommonOps;

public interface IterableCursor<T> extends Iterable<T>, ICursor<T>
{
    @Override
    default public <A extends Collection<? super T>> A into(final A target)
    {
        forEach(target::add);

        return target;
    }

    @Override
    default public Iterator<T> iterator()
    {
        return this;
    }

    @Override
    default public void forEach(final Consumer<? super T> action)
    {
        CommonOps.requireNonNull(action);

        for (final T t : this)
        {
            action.accept(t);
        }
        try
        {
            close();
        }
        catch (final IOException e)
        {
            onFailure(e);
        }
    }

    default public Stream<T> stream()
    {
        return StreamSupport.stream(spliterator(), false).onClose(() -> {
            try
            {
                close();
            }
            catch (final IOException e)
            {
                onFailure(e);
            }
        });
    }
}
