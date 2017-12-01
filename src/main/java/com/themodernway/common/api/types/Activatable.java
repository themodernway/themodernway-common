/*
 * Copyright (c) 2017, 2018, The Modern Way. All rights reserved.
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

import java.util.concurrent.atomic.AtomicBoolean;

public class Activatable implements IActivatable
{
    private static final long   serialVersionUID = 4994910595915077646L;

    private final AtomicBoolean m_active;

    public Activatable()
    {
        this(false);
    }

    public Activatable(final boolean active)
    {
        m_active = new AtomicBoolean(active);
    }

    @Override
    public boolean isActive()
    {
        return m_active.get();
    }

    @Override
    public boolean setActive(final boolean active)
    {
        return (m_active.getAndSet(active) != active);
    }
}
