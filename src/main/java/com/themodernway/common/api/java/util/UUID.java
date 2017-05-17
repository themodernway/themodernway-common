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

package com.themodernway.common.api.java.util;

import java.util.Random;

public final class UUID
{
    private static final int    UUID_LENGTH = 36;

    private static final char   UUID_DASHCH = '-';

    private static char         UUID_FOURCH = '4';
    
    private static final Random UUID_RANDOM = new Random();

    private static char[]       UUID_CARRAY = StringOps.HEXIDECIMAL_STRING.toCharArray();

    private static String       UUID_LOOKUP = StringOps.HEXIDECIMAL_STRING + UUID_DASHCH;

    protected UUID()
    {
    }

    public static final String uuid()
    {
        final char[] uuid = new char[UUID_LENGTH];

        // rfc4122 requires these characters

        uuid[8] = uuid[13] = uuid[18] = uuid[23] = UUID_DASHCH;

        uuid[14] = UUID_FOURCH;

        // Fill in random data. At i==19 set the high bits of clock sequence as
        // per rfc4122, sec. 4.1.5

        for (int i = 0; i < UUID_LENGTH; i++)
        {
            if (uuid[i] == 0)
            {
                final int r = UUID_RANDOM.nextInt(16);

                uuid[i] = UUID_CARRAY[(i == 19) ? (r & 0x3) | 0x8 : r & 0xf];
            }
        }
        return new String(uuid);
    }

    public static final boolean good(String uuid)
    {
        if (null == uuid)
        {
            return false;
        }
        uuid = uuid.trim();

        if (uuid.length() != UUID_LENGTH)
        {
            return false;
        }
        final char[] buff = uuid.toCharArray();

        if ((buff[8] != UUID_DASHCH) || (buff[13] != UUID_DASHCH) || (buff[18] != UUID_DASHCH) || (buff[23] != UUID_DASHCH) || (buff[14] != UUID_FOURCH))
        {
            return false;
        }
        for (int i = 0; i < UUID_LENGTH; i++)
        {
            if (UUID_LOOKUP.indexOf(buff[i]) < 0)
            {
                return false;
            }
        }
        return true;
    }
}
