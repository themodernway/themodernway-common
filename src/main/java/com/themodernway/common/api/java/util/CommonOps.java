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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.themodernway.common.api.types.ICursor;
import com.themodernway.common.api.types.IFixedIterable;

public final class CommonOps
{
    public static final int    IS_NOT_FOUND  = (0 - 1);

    public static final String CHARSET_UTF_8 = "UTF-8";

    private CommonOps()
    {
    }

    @SuppressWarnings("unchecked")
    public static final <T> T CAST(final Object object)
    {
        if (isNull(object))
        {
            return NULL();
        }
        try
        {
            return ((T) object);
        }
        catch (final ClassCastException e)
        {
            return NULL();
        }
    }

    public static final <T> T NULL()
    {
        return null;
    }

    public static final boolean isNull(final Object object)
    {
        return (null == object);
    }

    public static final boolean isNonNull(final Object object)
    {
        return (null != object);
    }

    public static final <T> T requireNonNullOrElse(final T value, final T otherwise)
    {
        return isNonNull(value) ? value : otherwise;
    }

    public static final <T> T requireNonNullOrElse(final T value, final Supplier<T> otherwise)
    {
        return isNonNull(value) ? value : otherwise.get();
    }

    public static final <T> T requireNonNull(final T value)
    {
        return Objects.requireNonNull(value);
    }

    public static final <T> T requireNonNull(final T value, final String reason)
    {
        return Objects.requireNonNull(value, reason);
    }

    public static final <T> T requireNonNull(final T value, final Supplier<String> reason)
    {
        return Objects.requireNonNull(value, reason);
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

    public static final <T> Optional<T> toOptional(final T valu)
    {
        return Optional.ofNullable(valu);
    }

    @SafeVarargs
    public static final <T> List<T> toList(final T... source)
    {
        return Arrays.asList(source);
    }

    public static final <T> List<T> toList(final Stream<T> stream)
    {
        return stream.collect(Collectors.toList());
    }

    public static final <T> List<T> toList(final Enumeration<? extends T> source)
    {
        return arrayList(source);
    }

    public static final <T> List<T> toList(final Collection<? extends T> collection)
    {
        return arrayList(collection);
    }

    public static final <T> List<T> toList(final ICursor<? extends T> cursor)
    {
        return arrayList(cursor);
    }

    public static final <T> List<T> toList(final IFixedIterable<? extends T> iterable)
    {
        return iterable.stream().collect(Collectors.toList());
    }

    public static final <T> List<T> emptyList()
    {
        return Collections.emptyList();
    }

    public static final <K, V> Map<K, V> emptyMap()
    {
        return Collections.emptyMap();
    }

    @SuppressWarnings("rawtypes")
    public static final <K, V> Map<K, V> CAST_RAW_MAP(final Map maps)
    {
        return CAST(maps);
    }

    public static final Map<String, Object> CAST_STR_MAP(final Map<String, ?> maps)
    {
        return CAST(maps);
    }

    public static final <T> List<T> keys(final Map<? extends T, ?> maps)
    {
        return arrayList(maps.keySet());
    }

    public static final <K, V> Map<K, V> toUnmodifiableMap(final Map<? extends K, ? extends V> maps)
    {
        return Collections.unmodifiableMap(maps);
    }

    public static final <T> List<T> toUnmodifiableList(final List<? extends T> list)
    {
        return Collections.unmodifiableList(list);
    }

    public static final <T> ArrayList<T> arrayList()
    {
        return new ArrayList<T>();
    }

    public static final <T> ArrayList<T> arrayList(final int size)
    {
        return new ArrayList<T>(size);
    }

    @SafeVarargs
    public static final <T> ArrayList<T> arrayList(final T... source)
    {
        return new ArrayList<T>(toList(source));
    }

    public static final <T> ArrayList<T> arrayList(final Stream<T> stream)
    {
        return new ArrayList<T>(stream.collect(Collectors.toList()));
    }

    public static final <T> ArrayList<T> arrayList(final Collection<? extends T> collection)
    {
        return new ArrayList<T>(collection);
    }

    public static final <T> ArrayList<T> arrayList(final ICursor<? extends T> cursor)
    {
        return cursor.into(arrayList());
    }

    public static final <T> ArrayList<T> arrayList(final IFixedIterable<? extends T> iterable)
    {
        return new ArrayList<T>(toList(iterable));
    }

    public static final <T> ArrayList<T> arrayList(final Enumeration<? extends T> source)
    {
        final ArrayList<T> list = arrayList();

        while (source.hasMoreElements())
        {
            list.add(source.nextElement());
        }
        return list;
    }

    public static final <T> T[] toArray(final Collection<T> collection, final T[] base)
    {
        return collection.toArray(base);
    }

    public static final <T> T[] toArray(final Collection<T> collection, final IntFunction<T[]> generator)
    {
        return toArray(collection, generator.apply(collection.size()));
    }

    public static final <T> T[] toArray(final Stream<T> stream, final IntFunction<T[]> generator)
    {
        return stream.toArray(generator);
    }

    @SafeVarargs
    public static final <T> T[] toArray(final T... source)
    {
        return Arrays.copyOf(source, source.length);
    }

    public static final int[] toArray(final int... source)
    {
        return Arrays.copyOf(source, source.length);
    }

    public static final long[] toArray(final long... source)
    {
        return Arrays.copyOf(source, source.length);
    }

    public static final double[] toArray(final double... source)
    {
        return Arrays.copyOf(source, source.length);
    }

    public static final boolean[] toArray(final boolean... source)
    {
        return Arrays.copyOf(source, source.length);
    }
}
