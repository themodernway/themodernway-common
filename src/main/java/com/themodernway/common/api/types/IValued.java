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

/**
 * Represents a class that has a value.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the method {@link #getValue()} is invoked.
 *
 * @param <T> the type of value that is returned.
 *
 * @author   Dean S. Jones
 * @since    1.0
 */
@FunctionalInterface
public interface IValued<T>
{
    public T getValue();
}
