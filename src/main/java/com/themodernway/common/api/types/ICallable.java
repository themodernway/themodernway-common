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

import java.util.concurrent.Callable;

import com.themodernway.common.api.java.util.CommonOps;

@FunctionalInterface
public interface ICallable<T, R> extends Callable<R>, Runnable
{
    public R call(T self, Object... args) throws Exception;

    @Override
    default R call() throws Exception
    {
        return call(CommonOps.CAST(this));
    }

    @Override
    default void run()
    {
        try
        {
            call();
        }
        catch (final Exception e)
        {
           throw new FrameworkException(e);
        }
    }
}
