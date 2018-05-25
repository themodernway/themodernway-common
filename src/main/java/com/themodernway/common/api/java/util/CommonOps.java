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

package com.themodernway.common.api.java.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.themodernway.common.api.types.ICursor;
import com.themodernway.common.api.types.IFixedIterable;

public final class CommonOps
{
    public static final int IS_NOT_FOUND = (0 - 1);

    private CommonOps()
    {
    }

    public static final boolean test()
    {
        return isNonNull(new CommonOps());
    }

    @SuppressWarnings("unchecked")
    public static final <T> T CAST(final Object value)
    {
        return ((T) value);
    }

    public static final <T> T SAFE(final Object valu, final Class<T> type)
    {
        if ((null != valu) && (type.isInstance(valu)))
        {
            return type.cast(valu);
        }
        return NULL();
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

    public static final boolean equals(final Object a, final Object b)
    {
        return Objects.equals(a, b);
    }

    public static final <T> T requireNonNullOrElse(final T value, final T otherwise)
    {
        return (null != value) ? value : otherwise;
    }

    public static final <T> T requireNonNullOrElse(final T value, final Supplier<T> otherwise)
    {
        return (null != value) ? value : otherwise.get();
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

    public static final <T, A extends List<? super T>> A reverse(final A source)
    {
        Collections.reverse(source);

        return source;
    }

    public static final <T, A extends List<? super T>> A shuffle(final A source)
    {
        Collections.shuffle(source);

        return source;
    }

    public static final <T, A extends List<? super T>> A shuffle(final A source, final Random random)
    {
        Collections.shuffle(source, random);

        return source;
    }

    @SafeVarargs
    public static final <T> Set<T> toSet(final T... source)
    {
        return toStream(source).collect(Collectors.toSet());
    }

    public static final <T> Set<T> toSet(final Stream<T> source)
    {
        final Set<T> list = source.collect(Collectors.toSet());

        source.close();

        return list;
    }

    @SafeVarargs
    public static final <T> List<T> toList(final T... source)
    {
        return Arrays.asList(source);
    }

    public static final <T> List<T> toList(final Stream<T> source)
    {
        final List<T> list = source.collect(Collectors.toList());

        source.close();

        return list;
    }

    public static final <T> List<T> toList(final Stream<T> source, final Predicate<? super T> predicate)
    {
        return toList(source.filter(predicate));
    }

    public static final <T> List<T> toList(final Enumeration<? extends T> source)
    {
        return arrayList(source);
    }

    public static final <T> List<T> toList(final Collection<? extends T> source)
    {
        return arrayList(source);
    }

    public static final <T> List<T> toList(final ICursor<? extends T> source)
    {
        return arrayList(source);
    }

    public static final <T> List<T> toList(final IFixedIterable<? extends T> source)
    {
        return arrayList(source);
    }

    public static final <T> Set<T> emptySet()
    {
        return Collections.emptySet();
    }

    public static final <T> Set<T> singletonSet(final T source)
    {
        return Collections.singleton(source);
    }

    public static final <T> List<T> emptyList()
    {
        return Collections.emptyList();
    }

    public static final <T> List<T> singletonList(final T source)
    {
        return Collections.singletonList(source);
    }

    public static final <K, V> Map<K, V> emptyMap()
    {
        return Collections.emptyMap();
    }

    public static final <T> Enumeration<T> emptyEnumeration()
    {
        return Collections.emptyEnumeration();
    }

    public static final <K, V> LinkedHashMap<K, V> linkedMap()
    {
        return new LinkedHashMap<>();
    }

    public static final <K, V> Map<K, V> singletonMap(final K k, final V v)
    {
        return Collections.singletonMap(k, v);
    }

    public static final <T> LinkedHashSet<T> linkedSet()
    {
        return new LinkedHashSet<>();
    }

    public static final <T> LinkedHashSet<T> linkedSet(final Collection<? extends T> source)
    {
        return new LinkedHashSet<>(source);
    }

    public static final <K, V> LinkedHashMap<K, V> linkedMap(final Map<? extends K, ? extends V> source)
    {
        return new LinkedHashMap<>(source);
    }

    @SuppressWarnings("rawtypes")
    public static final <K, V> Map<K, V> rawmap(final Map source)
    {
        return CAST(source);
    }

    public static final Map<String, Object> strmap(final Map<String, ?> source)
    {
        return CAST(source);
    }

    public static final <T> List<T> toKeys(final Map<? extends T, ?> source)
    {
        return arrayList(source.keySet());
    }

    public static final <K, V> Map<K, V> toUnmodifiableMap(final Map<? extends K, ? extends V> source)
    {
        return Collections.unmodifiableMap(source);
    }

    public static final <T> List<T> toUnmodifiableList(final Collection<? extends T> source)
    {
        if (source instanceof List)
        {
            return Collections.unmodifiableList(CAST(source));
        }
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> List<T> toUnmodifiableList(final Stream<T> source)
    {
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> List<T> toUnmodifiableList(final Stream<T> source, final Predicate<? super T> predicate)
    {
        return Collections.unmodifiableList(toList(source, predicate));
    }

    @SafeVarargs
    public static final <T> List<T> toUnmodifiableList(final T... source)
    {
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> List<T> toUnmodifiableList(final ICursor<? extends T> source)
    {
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> List<T> toUnmodifiableList(final IFixedIterable<? extends T> source)
    {
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> List<T> toUnmodifiableList(final Enumeration<? extends T> source)
    {
        return Collections.unmodifiableList(toList(source));
    }

    public static final <T> Set<T> toUnmodifiableSet(final Collection<? extends T> source)
    {
        if (source instanceof Set)
        {
            return Collections.unmodifiableSet(CAST(source));
        }
        return Collections.unmodifiableSet(linkedSet(source));
    }

    @SafeVarargs
    public static final <T> List<T> toListOfLists(final List<T>... lists)
    {
        final List<T> list = arrayList();

        for (final List<T> adds : lists)
        {
            if ((null != adds) && (false == adds.isEmpty()))
            {
                list.addAll(adds);
            }
        }
        return list;
    }

    @SafeVarargs
    public static final <T> List<T> toListOfListsUnique(final List<T>... lists)
    {
        final LinkedHashSet<T> look = linkedSet();

        for (final List<T> adds : lists)
        {
            if ((null != adds) && (false == adds.isEmpty()))
            {
                look.addAll(adds);
            }
        }
        return toList(look);
    }

    public static final <T> ArrayList<T> arrayListOfSize(final int size)
    {
        return new ArrayList<>(positive(size));
    }

    public static final <T> ArrayList<T> arrayList()
    {
        return new ArrayList<>();
    }

    @SafeVarargs
    public static final <T> ArrayList<T> arrayList(final T... source)
    {
        return new ArrayList<>(toList(source));
    }

    public static final <T> ArrayList<T> arrayList(final Stream<T> source)
    {
        return new ArrayList<>(toList(source));
    }

    public static final <T> ArrayList<T> arrayList(final Stream<T> source, final Predicate<? super T> predicate)
    {
        return new ArrayList<>(toList(source, predicate));
    }

    public static final <T> ArrayList<T> arrayList(final Collection<? extends T> source)
    {
        return new ArrayList<>(source);
    }

    public static final <T> ArrayList<T> arrayList(final ICursor<? extends T> source)
    {
        return source.into(arrayList());
    }

    public static final <T> ArrayList<T> arrayList(final IFixedIterable<? extends T> source)
    {
        return source.into(arrayList());
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
        return source.toArray(list);
    }

    public static final <T> T[] toArray(final Collection<T> source, final IntFunction<T[]> generator)
    {
        return toArray(source, generator.apply(source.size()));
    }

    public static final <T> T[] toArray(final Stream<T> source, final IntFunction<T[]> generator)
    {
        final T[] list = source.toArray(generator);

        source.close();

        return list;
    }

    public static final <T> T[] toArray(final Stream<T> source, final Predicate<? super T> predicate, final IntFunction<T[]> generator)
    {
        return toArray(source.filter(predicate), generator);
    }

    public static final <T> Stream<T> emptyStream()
    {
        return Stream.empty();
    }

    public static final <T> Stream<T> toStream(final T source)
    {
        return Stream.of(source);
    }

    @SafeVarargs
    public static final <T> Stream<T> toStream(final T... source)
    {
        if ((null == source) || (source.length == 0))
        {
            return Stream.empty();
        }
        if (source.length == 1)
        {
            return Stream.of(source[0]);
        }
        return Stream.of(source);
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

    public static final IntStream toStream(final int source)
    {
        return IntStream.of(source);
    }

    public static final IntStream toStream(final int... source)
    {
        if ((null == source) || (source.length == 0))
        {
            return IntStream.empty();
        }
        if (source.length == 1)
        {
            return IntStream.of(source[0]);
        }
        return IntStream.of(source);
    }

    public static final LongStream toStream(final long source)
    {
        return LongStream.of(source);
    }

    public static final LongStream toStream(final long... source)
    {
        if ((null == source) || (source.length == 0))
        {
            return LongStream.empty();
        }
        if (source.length == 1)
        {
            return LongStream.of(source[0]);
        }
        return LongStream.of(source);
    }

    public static final DoubleStream toStream(final double source)
    {
        return DoubleStream.of(source);
    }

    public static final DoubleStream toStream(final double... source)
    {
        if ((null == source) || (source.length == 0))
        {
            return DoubleStream.empty();
        }
        if (source.length == 1)
        {
            return DoubleStream.of(source[0]);
        }
        return DoubleStream.of(source);
    }

    public static final <T> Queue<T> lifo(final Deque<T> deque)
    {
        return Collections.asLifoQueue(requireNonNull(deque));
    }

    public static final boolean all(final Collection<?> arg0, final Collection<?> arg1)
    {
        return arg0.containsAll(requireNonNull(arg1));
    }

    public static final boolean any(final Collection<?> arg0, final Collection<?> arg1)
    {
        return (false == none(arg0, arg1));
    }

    public static final boolean none(final Collection<?> arg0, final Collection<?> arg1)
    {
        return Collections.disjoint(requireNonNull(arg0), requireNonNull(arg1));
    }

    public static final int box(final int val, final int min, final int max)
    {
        return Math.min(Math.max(val, min), max);
    }

    public static final long box(final long val, final long min, final long max)
    {
        return Math.min(Math.max(val, min), max);
    }

    public static final double box(final double val, final double min, final double max)
    {
        return Math.min(Math.max(val, min), max);
    }

    public static final int positive(final int val)
    {
        return Math.max(0, val);
    }

    public static final long positive(final long val)
    {
        return Math.max(0L, val);
    }

    public static final double positive(final double val)
    {
        return Math.max(0d, val);
    }

    public static final double project(final double value, final double istart, final double istop, final double ostart, final double ostop)
    {
        return ostart + ((ostop - ostart) * ((value - istart) / (istop - istart)));
    }

    public static final long getCurrentClock()
    {
        return System.currentTimeMillis();
    }

    public static final long getCurrentNanos()
    {
        return System.nanoTime();
    }
}
