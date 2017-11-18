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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
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
    public static final <T> T CAST(final Object value)
    {
        if (isNull(value))
        {
            return NULL();
        }
        try
        {
            return ((T) value);
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

    public static final boolean isNull(final Object value)
    {
        return (null == value);
    }

    public static final boolean isNonNull(final Object value)
    {
        return (null != value);
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

    public static final <T> Supplier<T> toSupplier(final T value)
    {
        return () -> value;
    }

    public static final IntSupplier toSupplier(final int value)
    {
        return () -> value;
    }

    public static final LongSupplier toSupplier(final long value)
    {
        return () -> value;
    }

    public static final DoubleSupplier toSupplier(final double value)
    {
        return () -> value;
    }

    public static final BooleanSupplier toSupplier(final boolean value)
    {
        return () -> value;
    }

    public static final <T> Optional<T> toOptional(final T value)
    {
        return Optional.ofNullable(value);
    }

    @SafeVarargs
    public static final <T> List<T> toList(final T... source)
    {
        return Arrays.asList(requireNonNull(source));
    }

    public static final <T> List<T> toList(final Stream<T> source)
    {
        return source.collect(Collectors.toList());
    }

    public static final <T> List<T> toList(final Enumeration<? extends T> source)
    {
        return arrayList(requireNonNull(source));
    }

    public static final <T> List<T> toList(final Collection<? extends T> source)
    {
        return arrayList(requireNonNull(source));
    }

    public static final <T> List<T> toList(final ICursor<? extends T> source)
    {
        return arrayList(requireNonNull(source));
    }

    public static final <T> List<T> toList(final IFixedIterable<? extends T> source)
    {
        return arrayList(requireNonNull(source));
    }

    public static final <T> List<T> emptyList()
    {
        return Collections.emptyList();
    }

    public static final <K, V> Map<K, V> emptyMap()
    {
        return Collections.emptyMap();
    }

    public static final <K, V> HashMap<K, V> hashMap()
    {
        return new HashMap<K, V>();
    }

    public static final <K, V> HashMap<K, V> hashMap(final Map<? extends K, ? extends V> source)
    {
        return new HashMap<K, V>(requireNonNull(source));
    }

    public static final <K, V> LinkedHashMap<K, V> linkedMap()
    {
        return new LinkedHashMap<K, V>();
    }

    public static final <K, V> LinkedHashMap<K, V> linkedMap(final Map<? extends K, ? extends V> source)
    {
        return new LinkedHashMap<K, V>(requireNonNull(source));
    }

    public static final <K, V> Map<K, V> computedMap(final Collection<? extends K> source, final Function<K, V> builds)
    {
        requireNonNull(source);

        requireNonNull(builds);

        final Map<K, V> linked = linkedMap();

        if (false == source.isEmpty())
        {
            source.forEach(mapkey -> {

                if (null != mapkey)
                {
                    linked.put(mapkey, builds.apply(mapkey));
                }
            });
        }
        return linked;
    }

    @SuppressWarnings("rawtypes")
    public static final <K, V> Map<K, V> RAWMAP(final Map source)
    {
        return CAST(requireNonNull(source));
    }

    public static final Map<String, Object> STRMAP(final Map<String, ?> source)
    {
        return CAST(requireNonNull(source));
    }

    public static final <T> List<T> toKeys(final Map<? extends T, ?> source)
    {
        return arrayList(source.keySet());
    }

    public static final <K, V> Map<K, V> toUnmodifiableMap(final Map<? extends K, ? extends V> source)
    {
        return Collections.unmodifiableMap(requireNonNull(source));
    }

    public static final <T> List<T> toUnmodifiableList(final Collection<? extends T> source)
    {
        if (requireNonNull(source).isEmpty())
        {
            return Collections.unmodifiableList(emptyList());
        }
        if (source instanceof List)
        {
            return Collections.unmodifiableList(CAST(source));
        }
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> ArrayList<T> arrayList()
    {
        return new ArrayList<T>();
    }

    public static final <T> ArrayList<T> arrayList(final int size)
    {
        return new ArrayList<T>(Math.max(0, size));
    }

    @SafeVarargs
    public static final <T> ArrayList<T> arrayList(final T... source)
    {
        return new ArrayList<T>(toList(requireNonNull(source)));
    }

    public static final <T> ArrayList<T> arrayList(final Stream<T> source)
    {
        return new ArrayList<T>(toList(requireNonNull(source)));
    }

    public static final <T> ArrayList<T> arrayList(final Collection<? extends T> source)
    {
        return new ArrayList<T>(requireNonNull(source));
    }

    public static final <T> ArrayList<T> arrayList(final ICursor<? extends T> source)
    {
        return source.into(arrayList());
    }

    public static final <T> ArrayList<T> arrayList(final IFixedIterable<? extends T> source)
    {
        return source.into(arrayList(source.size()));
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

    public static final <T> T[] toArray(final Collection<T> source, final T[] list)
    {
        return source.toArray(requireNonNull(list));
    }

    public static final <T> T[] toArray(final Collection<T> source, final IntFunction<T[]> generator)
    {
        return toArray(requireNonNull(source), requireNonNull(generator).apply(source.size()));
    }

    public static final <T> T[] toArray(final Stream<T> source, final IntFunction<T[]> generator)
    {
        return source.toArray(requireNonNull(generator));
    }

    @SafeVarargs
    public static final <T> T[] toArray(final T... source)
    {
        return Arrays.copyOf(requireNonNull(source), source.length);
    }

    public static boolean all(final Collection<?> arg0, final Collection<?> arg1)
    {
        return requireNonNull(arg0).containsAll(requireNonNull(arg1));
    }

    public static boolean any(final Collection<?> arg0, final Collection<?> arg1)
    {
        return (false == none(requireNonNull(arg0), requireNonNull(arg1)));
    }

    public static boolean none(final Collection<?> arg0, final Collection<?> arg1)
    {
        return Collections.disjoint(requireNonNull(arg0), requireNonNull(arg1));
    }
}
