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

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.themodernway.common.api.hash.Hasher;
import com.themodernway.common.api.hash.IHasher;

public final class SHA512Helper implements IHasher
{
    private final Hasher m_hasher;

    public SHA512Helper()
    {
        m_hasher = new Hasher(this);
    }

    @Override
    public String sha512(final CharSequence text)
    {
        return Hex.encodeHexString(DigestUtils.getSha512Digest().digest(text.toString().getBytes(Charsets.UTF_8)));
    }

    @Override
    public String sha512(final CharSequence text, final String salt)
    {
        return m_hasher.sha512(text, salt);
    }

    @Override
    public String sha512(final CharSequence text, final String salt, final int iter)
    {
        return m_hasher.sha512(text, salt, iter);
    }

    @Override
    public String sha512(final CharSequence text, final int iter)
    {
        return m_hasher.sha512(text, iter);
    }
}
