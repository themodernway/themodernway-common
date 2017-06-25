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

package com.themodernway.common.api.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class CommonOps
{
    public static final int    IS_NOT_FOUND  = (0 - 1);

    public static final String CHARSET_UTF_8 = "UTF-8";

    private CommonOps()
    {
    }

    public static final <T> T toNULL()
    {
        return null;
    }

    public static final <T> Supplier<T> toSupplier(final T valu)
    {
        return () -> valu;
    }

    public static final IntSupplier toSupplier(final int valu)
    {
        return () -> valu;
    }

    public static final LongSupplier toSupplier(final long valu)
    {
        return () -> valu;
    }

    public static final DoubleSupplier toSupplier(final double valu)
    {
        return () -> valu;
    }

    public static final BooleanSupplier toSupplier(final boolean valu)
    {
        return () -> valu;
    }

    public static final <T> Optional<T> toOptionl(final T valu)
    {
        return Optional.ofNullable(valu);
    }

    public static final <T> List<T> toList(final Stream<T> stream)
    {
        return stream.collect(Collectors.toList());
    }

    public static final <T> List<T> toList(final Collection<T> collection)
    {
        if (collection instanceof List)
        {
            collection.parallelStream();
            return ((List<T>) collection);
        }
        else
        {
            return new ArrayList<T>(collection);
        }
    }

    public static final <T> List<T> toList(final Iterable<T> iter)
    {
        return toList(StreamSupport.stream(iter.spliterator(), false));
    }
}
