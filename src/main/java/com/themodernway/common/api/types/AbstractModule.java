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

import com.themodernway.common.api.java.util.CommonOps;

public abstract class AbstractModule<T extends AbstractModule<T>> extends AbstractModuleBase<T, T>
{
    protected AbstractModule(final String name, final String vers)
    {
        this(name, vers, true, CommonOps.NULL());
    }

    protected AbstractModule(final String name, final String vers, final boolean active)
    {
        this(name, vers, active, CommonOps.NULL());
    }

    protected AbstractModule(final String name, final String vers, final ICallable<T, T> exec)
    {
        this(name, vers, true, exec);
    }

    protected AbstractModule(final String name, final String vers, final boolean active, final ICallable<T, T> exec)
    {
        super(name, vers, active, exec);
    }
}
