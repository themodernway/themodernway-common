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

package com.themodernway.common.api.hash;

import com.themodernway.common.api.java.util.CommonOps;

public final class Hasher implements IHasher
{
    private final IHash512 m_hash;

    public Hasher(final IHash512 hash)
    {
        m_hash = CommonOps.requireNonNull(hash);
    }

    @Override
    public String sha512(final CharSequence text)
    {
        return m_hash.sha512(text);
    }

    @Override
    public String sha512(final CharSequence text, final String salt)
    {
        return sha512(text, salt, SHA512_ITERATIONS);
    }

    @Override
    public String sha512(final CharSequence text, final int iter)
    {
        return sha512(text, null, iter);
    }

    @Override
    public String sha512(final CharSequence text, final String salt, final int iter)
    {
        String tval = text.toString();

        if ((null == salt) || (salt.isEmpty()))
        {
            if (iter < 2)
            {
                return sha512(tval);
            }
            for (int i = 0; i < iter; i++)
            {
                tval = sha512(tval);
            }
            return tval;
        }
        if (iter < 2)
        {
            return sha512(tval + salt);
        }
        for (int i = 0; i < iter; i++)
        {
            if ((i % 2) == 0)
            {
                tval = sha512(tval + salt);
            }
            else
            {
                tval = sha512(salt + tval);
            }
        }
        return tval;
    }
}
