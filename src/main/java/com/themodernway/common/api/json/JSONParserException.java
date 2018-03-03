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

package com.themodernway.common.api.json;

import com.themodernway.common.api.java.util.CommonOps;

public class JSONParserException extends Exception
{
    private static final long serialVersionUID           = 2577571614214539296L;

    public static final int   ERROR_UNEXPECTED_CHAR      = 0;

    public static final int   ERROR_UNEXPECTED_TOKEN     = 1;

    public static final int   ERROR_UNEXPECTED_EXCEPTION = 2;

    private int               m_errorType                = ERROR_UNEXPECTED_EXCEPTION;

    private Object            m_unexpectedObject         = CommonOps.nulled();

    private int               m_position                 = CommonOps.IS_NOT_FOUND;

    public JSONParserException(final Throwable e)
    {
        super(e);
    }

    public JSONParserException(final int errorType)
    {
        this(CommonOps.IS_NOT_FOUND, errorType, null);
    }

    public JSONParserException(final int errorType, final Object unexpectedObject)
    {
        this(CommonOps.IS_NOT_FOUND, errorType, unexpectedObject);
    }

    public JSONParserException(final int position, final int errorType, final Object unexpectedObject)
    {
        m_position = position;

        m_errorType = errorType;

        m_unexpectedObject = unexpectedObject;
    }

    public int getErrorType()
    {
        return m_errorType;
    }

    public void setErrorType(final int errorType)
    {
        m_errorType = errorType;
    }

    public int getPosition()
    {
        return m_position;
    }

    public void setPosition(final int position)
    {
        m_position = position;
    }

    public Object getUnexpectedObject()
    {
        return m_unexpectedObject;
    }

    public void setUnexpectedObject(final Object unexpectedObject)
    {
        m_unexpectedObject = unexpectedObject;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();

        switch (getErrorType())
        {
            case ERROR_UNEXPECTED_CHAR:
                sb.append("Unexpected character (").append(getUnexpectedObject()).append(") at position ").append(getPosition()).append('.');
                break;
            case ERROR_UNEXPECTED_TOKEN:
                sb.append("Unexpected token ").append(getUnexpectedObject()).append(" at position ").append(getPosition()).append('.');
                break;
            case ERROR_UNEXPECTED_EXCEPTION:
                sb.append("Unexpected exception at position ").append(getPosition()).append(": ").append(getUnexpectedObject());
                break;
            default:
                sb.append("Unkown error at position ").append(getPosition()).append('.');
                break;
        }
        return sb.toString();
    }
}
