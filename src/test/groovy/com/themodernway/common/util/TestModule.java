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

package com.themodernway.common.util;

import java.util.concurrent.atomic.AtomicInteger;

import com.themodernway.common.api.types.AbstractModule;
import com.themodernway.common.api.types.ICallable;

public class TestModule extends AbstractModule<TestModule>
{
    private final AtomicInteger m_valu = new AtomicInteger(0);

    public TestModule()
    {
        super("TestModule", "2.1.2-RELEASE", true, new TestModuleAction());
    }

    public int getValue()
    {
        return m_valu.get();
    }

    private static final class TestModuleAction implements ICallable<TestModule, TestModule>
    {
        @Override
        public TestModule call(final TestModule self, final Object... args)
        {
            self.m_valu.incrementAndGet();

            return self;
        }
    }
}
