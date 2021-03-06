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

import java.util.Date;

public interface IMixedListDefinition<L extends IMixedListDefinition<L, O>, O extends IMixedStringHashDefinition<L, O>> extends IDeepCopied<L>, IMixedList
{
    public L getAsArray(int index);

    public Date getAsDate(int index);

    public Boolean getAsBoolean(int index);

    public Double getAsDouble(int index);

    public Integer getAsInteger(int index);

    public Number getAsNumber(int index);

    public O getAsObject(int index);

    public String getAsString(int index);

    public INativeFunction<?> getAsNativeFunction(int index);

    public L reverse();

    public <T> T pop();

    public <T> T shift();

    public L push(Object value);

    public L push(Object... objects);

    public L put(int index, Object value);

    public L fill(Object value);

    public L fill(Object value, int beg);

    public L fill(Object value, int beg, int end);

    public L unshift(Object value);

    public L unshift(Object... objects);
}
