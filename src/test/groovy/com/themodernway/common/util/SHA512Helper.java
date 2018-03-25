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

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.themodernway.common.api.hash.Hasher;
import com.themodernway.common.api.hash.IHasher;
import com.themodernway.common.api.java.util.CommonOps;
import com.themodernway.common.api.java.util.StringOps;

public final class SHA512Helper implements IHasher
{
    public static final Charset UTF_8_CHARSET = Charset.forName(StringOps.CHARSET_UTF_8);

    private final Hasher        m_hasher      = new Hasher(this);

    public SHA512Helper()
    {
    }

    @Override
    public String sha512(final String text)
    {
        return Hex.encodeHexString(DigestUtils.getSha512Digest().digest(text.getBytes(UTF_8_CHARSET)));
    }

    @Override
    public String sha512(final String text, final String salt)
    {
        return m_hasher.sha512(CommonOps.requireNonNull(text), salt);
    }

    @Override
    public String sha512(final String text, final String salt, final int iter)
    {
        return m_hasher.sha512(CommonOps.requireNonNull(text), salt, iter);
    }

    @Override
    public String sha512(final String text, final int iter)
    {
        return m_hasher.sha512(CommonOps.requireNonNull(text), iter);
    }
}
