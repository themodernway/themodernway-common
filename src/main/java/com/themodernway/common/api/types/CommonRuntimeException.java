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

public class CommonRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 8390310509855255473L;

    public CommonRuntimeException()
    {
        super();
    }

    public CommonRuntimeException(final Throwable cause)
    {
        super(cause);
    }

    public CommonRuntimeException(final String message)
    {
        super(message);
    }

    public CommonRuntimeException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    protected CommonRuntimeException(final String message, final Throwable cause, final boolean suppress, final boolean trace)
    {
        super(message, cause, suppress, trace);
    }
}
