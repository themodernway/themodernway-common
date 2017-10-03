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

import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.themodernway.common.api.java.util.CommonOps;

@FunctionalInterface
public interface BooleanOp
{
    public boolean test();

    default public BooleanOp and(final BooleanOp op)
    {
        CommonOps.requireNonNull(op);

        return () -> (test() && op.test());
    }

    default public BooleanOp or(final BooleanOp op)
    {
        CommonOps.requireNonNull(op);

        return () -> (test() || op.test());
    }

    default public BooleanOp not()
    {
        return () -> (false == test());
    }

    public static <T> BooleanOp compose(final T valu, final Predicate<T> pred)
    {
        CommonOps.requireNonNull(pred);

        return () -> pred.test(valu);
    }

    public static BooleanOp of(final BooleanOp op)
    {
        return CommonOps.requireNonNull(op);
    }

    public static <T> BooleanOp compose(final Supplier<T> valu, final Predicate<T> pred)
    {
        CommonOps.requireNonNull(valu);

        CommonOps.requireNonNull(pred);

        return () -> pred.test(valu.get());
    }

    public static BooleanOp of(final boolean op)
    {
        return () -> op;
    }

    public static BooleanOp of(final BooleanSupplier op)
    {
        CommonOps.requireNonNull(op);

        return () -> op.getAsBoolean();
    }

    public static BooleanOp of(final Supplier<Boolean> op)
    {
        CommonOps.requireNonNull(op);

        return () -> Optional.ofNullable(op.get()).orElse(false);
    }

    public static BooleanOp any(final BooleanOp... ops)
    {
        final int size = ops.length;

        if (size < 1)
        {
            return () -> false;
        }
        return () -> {

            for (int i = 0; i < size; i++)
            {
                if (ops[i].test())
                {
                    return true;
                }
            }
            return false;
        };
    }

    public static BooleanOp any(final List<BooleanOp> ops)
    {
        final int size = ops.size();

        if (size < 1)
        {
            return () -> false;
        }
        return () -> {

            for (int i = 0; i < size; i++)
            {
                if (ops.get(i).test())
                {
                    return true;
                }
            }
            return false;
        };
    }

    public static BooleanOp all(final BooleanOp... ops)
    {
        final int size = ops.length;

        if (size < 1)
        {
            return () -> true;
        }
        return () -> {

            for (int i = 0; i < size; i++)
            {
                if (false == ops[i].test())
                {
                    return false;
                }
            }
            return true;
        };
    }

    public static BooleanOp all(final List<BooleanOp> ops)
    {
        final int size = ops.size();

        if (size < 1)
        {
            return () -> true;
        }
        return () -> {

            for (int i = 0; i < size; i++)
            {
                if (false == ops.get(i).test())
                {
                    return false;
                }
            }
            return true;
        };
    }

    public static abstract class AbstractPredicateBooleanOp<T> implements BooleanOp, Predicate<T>, IValued<T>
    {
        private final T m_value;

        public AbstractPredicateBooleanOp(final T value)
        {
            m_value = value;
        }

        @Override
        public boolean test()
        {
            return test(getValue());
        }

        @Override
        public T getValue()
        {
            return m_value;
        }
    }
}