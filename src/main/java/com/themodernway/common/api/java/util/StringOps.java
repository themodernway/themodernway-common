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

package com.themodernway.common.api.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StringOps
{
    public static final char                  ESCAPE_SLASH         = '\\';

    public static final char                  SINGLE_SLASH         = '/';

    public static final char                  SINGLE_QUOTE         = '\'';

    public static final char                  DOUBLE_QUOTE         = '"';

    public static final char                  DECIMAL_CHAR         = '.';

    public static final char                  UNX_SEPARATOR_CHAR   = SINGLE_SLASH;

    public static final char                  WIN_SEPARATOR_CHAR   = ESCAPE_SLASH;

    public static final String                NULL_STRING          = null;

    public static final String                EMPTY_STRING         = "";

    public static final String                SPACE_STRING         = " ";

    public static final String                PLACE_STRING         = ".";

    public static final String                COMMA_STRING         = ",";

    public static final String                MINUS_STRING         = "-";

    public static final String                START_ARRAY_STRING   = "[";

    public static final String                CLOSE_ARRAY_STRING   = "]";

    public static final String                SINGLE_QUOT_STRING   = "'";

    public static final String                DOUBLE_QUOT_STRING   = "\"";

    public static final String                NEWLINE_STRING       = "\n";

    public static final String                CARRAGE_STRING       = "\r";

    public static final String                NULL_AS_STRING       = "null";

    public static final String                HEXIDECIMAL_STRING   = "0123456789ABCDEF";

    public static final String                CHARSET_UTF_8        = "UTF-8";

    public static final String                EMPTY_ARRAY_STRING   = START_ARRAY_STRING + CLOSE_ARRAY_STRING;

    public static final String                COMMA_LIST_TOKENIZER = COMMA_STRING;

    public static final String                COMMA_LIST_SEPARATOR = COMMA_STRING + SPACE_STRING;

    public static final String[]              EMPTY_STRING_ARRAY   = new String[0];

    public static final IntFunction<String[]> BUILD_STRING_ARRAY   = String[]::new;

    public static final Predicate<String>     STRING_NULL_FILTER   = Objects::nonNull;

    private StringOps()
    {
    }

    public static final boolean test()
    {
        return CommonOps.isNonNull(new StringOps());
    }

    public static final String repeat(final String string, final int times)
    {
        if ((null == string) || (string.isEmpty()) || (times < 2))
        {
            return string;
        }
        final StringBuilder builder = new StringBuilder(string.length() * times);

        for (int i = 0; i < times; i++)
        {
            builder.append(string);
        }
        return builder.toString();
    }

    public static final void setConsumerUniqueStringArray(final String list, final Consumer<String[]> prop)
    {
        CommonOps.requireNonNull(prop);

        final String toks = toTrimOrNull(list);

        if (null != toks)
        {
            final String[] uniq = toArray(toUniqueTokenStringList(toks));

            if ((null != uniq) && (uniq.length > 0))
            {
                prop.accept(uniq);

                return;
            }
        }
        prop.accept(null);
    }

    public static final void setConsumerUniqueStringArray(final Collection<String> list, final Consumer<String[]> prop)
    {
        CommonOps.requireNonNull(prop);

        if ((null != list) && (false == list.isEmpty()))
        {
            final String[] uniq = toUniqueArray(list);

            if ((null != uniq) && (uniq.length > 0))
            {
                prop.accept(uniq);

                return;
            }
        }
        prop.accept(null);
    }

    public static final List<String> getSupplierUniqueStringArray(final Supplier<String[]> prop)
    {
        final String[] uniq = CommonOps.requireNonNull(prop).get();

        if ((null != uniq) && (uniq.length > 0))
        {
            return toUnique(uniq);
        }
        return CommonOps.arrayListOfSize(0);
    }

    public static final String[] toArray(final Collection<String> collection)
    {
        return CommonOps.toArray(collection, BUILD_STRING_ARRAY);
    }

    public static final String[] toArray(final Collection<String> collection, final Predicate<String> predicate)
    {
        return toArray(collection.stream(), predicate);
    }

    public static final String[] toArray(final String... collection)
    {
        return CommonOps.toArray(collection);
    }

    public static final String[] toArray(final Stream<String> stream)
    {
        return CommonOps.toArray(stream, BUILD_STRING_ARRAY);
    }

    public static final String[] toArray(final Stream<String> stream, final Predicate<String> predicate)
    {
        return CommonOps.toArray(stream, predicate, BUILD_STRING_ARRAY);
    }

    public static final List<String> toList(final Stream<String> stream)
    {
        return CommonOps.toList(stream);
    }

    public static final List<String> toList(final Stream<String> stream, final Predicate<String> predicate)
    {
        return CommonOps.toList(stream, predicate);
    }

    public static final String[] toUniqueArray(final Collection<String> collection)
    {
        return toArray(toUnique(collection.stream()));
    }

    public static final String[] toUniqueArray(final Collection<String> collection, final Predicate<String> predicate)
    {
        return toArray(toUnique(collection.stream(), predicate));
    }

    public static final String[] toUniqueArray(final String... collection)
    {
        return toArray(toUnique(CommonOps.toStream(collection)));
    }

    public static final Stream<String> toUnique(final Stream<String> stream)
    {
        return stream.filter(STRING_NULL_FILTER).sequential().distinct();
    }

    public static final Stream<String> toUnique(final Stream<String> stream, final Predicate<String> predicate)
    {
        return stream.filter(STRING_NULL_FILTER.and(predicate)).sequential().distinct();
    }

    public static final List<String> toUnique(final String... collection)
    {
        return toList(toUnique(CommonOps.toStream(collection)));
    }

    public static final List<String> toUnique(final Collection<String> collection)
    {
        return toList(toUnique(collection.stream()));
    }

    public static final List<String> toUnique(final Collection<String> collection, final Predicate<String> predicate)
    {
        return toList(toUnique(collection.stream(), predicate));
    }

    public static final List<String> toUniqueTokenStringList(String strings)
    {
        strings = requireTrimOrNull(strings);

        if (strings.contains(COMMA_LIST_TOKENIZER))
        {
            return toUnique(tokenizeToStringCollection(strings, COMMA_LIST_TOKENIZER));
        }
        else
        {
            return toUnique(strings);
        }
    }

    public static final String toCommaSeparated(final Collection<String> collection)
    {
        return toCommaSeparated(collection.stream());
    }

    public static final String toCommaSeparated(final String... collection)
    {
        return toCommaSeparated(CommonOps.toStream(collection));
    }

    public static final String toCommaSeparated(final Stream<String> stream)
    {
        final String string = stream.collect(Collectors.joining(COMMA_LIST_SEPARATOR));

        stream.close();

        return string;
    }

    public static final Collection<String> tokenizeToStringCollection(final String string)
    {
        return tokenizeToStringCollection(string, true, true);
    }

    public static final Collection<String> tokenizeToStringCollection(final String string, final String delimiters)
    {
        return tokenizeToStringCollection(string, delimiters, true, true);
    }

    public static final Collection<String> tokenizeToStringCollection(final String string, final boolean trim, final boolean ignore)
    {
        return tokenizeToStringCollection(string, COMMA_LIST_TOKENIZER, trim, ignore);
    }

    public static final Collection<String> tokenizeToStringCollection(final String string, final String delimiters, final boolean trim, final boolean ignore)
    {
        if ((null == string) || (string.isEmpty()) || (null == delimiters) || (delimiters.isEmpty()))
        {
            return CommonOps.emptyList();
        }
        final ArrayList<String> list = CommonOps.arrayList();

        for (String token : string.split(delimiters))
        {
            if (trim)
            {
                token = token.trim();
            }
            if ((false == ignore) || (token.length() > 0))
            {
                list.add(token);
            }
        }
        return list;
    }

    public static final String toPrintableString(final Collection<String> collection)
    {
        if (null == collection)
        {
            return NULL_AS_STRING;
        }
        if (collection.isEmpty())
        {
            return EMPTY_ARRAY_STRING;
        }
        return collection.stream().map(s -> toEscapedString(s)).collect(Collectors.joining(COMMA_LIST_SEPARATOR, START_ARRAY_STRING, CLOSE_ARRAY_STRING));
    }

    public static final String toPrintableString(final String... list)
    {
        if (null == list)
        {
            return NULL_AS_STRING;
        }
        if (list.length == 0)
        {
            return EMPTY_ARRAY_STRING;
        }
        return CommonOps.toStream(list).map(s -> toEscapedString(s)).collect(Collectors.joining(COMMA_LIST_SEPARATOR, START_ARRAY_STRING, CLOSE_ARRAY_STRING));
    }

    public static final String toTrimOrNull(String string)
    {
        if (null == string)
        {
            return null;
        }
        string = string.trim();

        if (string.isEmpty())
        {
            return null;
        }
        return string;
    }

    public static final String toTrimOrElse(String string, final String otherwise)
    {
        string = toTrimOrNull(string);

        if (null == string)
        {
            return otherwise;
        }
        return string;
    }

    public static final String toTrimOrElse(String string, final Supplier<String> otherwise)
    {
        string = toTrimOrNull(string);

        if (null == string)
        {
            return otherwise.get();
        }
        return string;
    }

    public static final String requireTrimOrNull(final String string)
    {
        return CommonOps.requireNonNull(toTrimOrNull(string));
    }

    public static final String requireTrimOrNull(final String string, final String reason)
    {
        return CommonOps.requireNonNull(toTrimOrNull(string), reason);
    }

    public static final String requireTrimOrNull(final String string, final Supplier<String> reason)
    {
        return CommonOps.requireNonNull(toTrimOrNull(string), reason);
    }

    public static final boolean isDigits(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        for (int i = 0; i < leng; i++)
        {
            if (false == Character.isDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAlpha(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        for (int i = 0; i < leng; i++)
        {
            if (false == Character.isLetter(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAlphaOrDigits(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        for (int i = 0; i < leng; i++)
        {
            if (false == Character.isLetterOrDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAlphaOrDigitsStartsAlpha(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        if (false == Character.isLetter(string.charAt(0)))
        {
            return false;
        }
        for (int i = 1; i < leng; i++)
        {
            if (false == Character.isLetterOrDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final String reverse(final String string)
    {
        if (null == string)
        {
            return string;
        }
        if (string.length() < 2)
        {
            return string;
        }
        return new StringBuilder(string).reverse().toString();
    }

    public static final StringBuilder toUnicodeEscapedWithBuilder(final StringBuilder builder, final char c)
    {
        final String hex = Integer.toHexString(c).toUpperCase();

        final int pad = Math.max(Math.min(4 - hex.length(), 3), 0);

        if (0 == pad)
        {
            return builder.append("\\u").append(hex);
        }
        else if (1 == pad)
        {
            return builder.append("\\u0").append(hex);
        }
        else if (2 == pad)
        {
            return builder.append("\\u00").append(hex);
        }
        else
        {
            return builder.append("\\u000").append(hex);
        }
    }

    public static final boolean isSimpleAscii(final char c)
    {
        return (((c <= 'z') && (c >= ' ')) && ((c >= 'a') || ((c >= 'A') && (c <= 'Z')) || (c == ' ') || ((c >= '0') && (c <= '9'))));
    }

    public static final String toEscapedString(final String string)
    {
        return toEscapedString(string, true);
    }

    public static final String toEscapedString(final String string, final boolean quote)
    {
        if (null == string)
        {
            return NULL_AS_STRING;
        }
        return toEscapedStringWithBuilder(string, new StringBuilder(), quote).toString();
    }

    public static final StringBuilder toEscapedStringWithBuilder(final String string, final StringBuilder builder)
    {
        return toEscapedStringWithBuilder(string, builder, true);
    }

    public static final StringBuilder toEscapedStringWithBuilder(final String string, final StringBuilder builder, final boolean quote)
    {
        Objects.requireNonNull(builder, "null builder");

        if (null == string)
        {
            return builder.append(NULL_AS_STRING);
        }
        if (quote)
        {
            builder.append(DOUBLE_QUOT_STRING);
        }
        final int leng = string.length();

        for (int i = 0; i < leng; i++)
        {
            final char c = string.charAt(i);

            if (isSimpleAscii(c))
            {
                builder.append(c); // ASCII will be most common, this improves write speed about 5%, FWIW.
            }
            else
            {
                if (c > 0x7f)
                {
                    toUnicodeEscapedWithBuilder(builder, c);
                }
                else if (c < 32)
                {
                    switch (c)
                    {
                        case '\b':
                            builder.append(ESCAPE_SLASH).append('b');
                            break;
                        case '\n':
                            builder.append(ESCAPE_SLASH).append('n');
                            break;
                        case '\t':
                            builder.append(ESCAPE_SLASH).append('t');
                            break;
                        case '\f':
                            builder.append(ESCAPE_SLASH).append('f');
                            break;
                        case '\r':
                            builder.append(ESCAPE_SLASH).append('r');
                            break;
                        default:
                            toUnicodeEscapedWithBuilder(builder, c);
                            break;
                    }
                }
                else
                {
                    switch (c)
                    {
                        case SINGLE_QUOTE:
                            builder.append(ESCAPE_SLASH).append(SINGLE_QUOTE);
                            break;
                        case DOUBLE_QUOTE:
                            builder.append(ESCAPE_SLASH).append(DOUBLE_QUOTE);
                            break;
                        case ESCAPE_SLASH:
                            builder.append(ESCAPE_SLASH).append(ESCAPE_SLASH);
                            break;
                        case SINGLE_SLASH:
                            builder.append(ESCAPE_SLASH).append(SINGLE_SLASH);
                            break;
                        default:
                            builder.append(c);
                            break;
                    }
                }
            }
        }
        if (quote)
        {
            builder.append(DOUBLE_QUOT_STRING);
        }
        return builder;
    }

    public static final String failIfNullBytePresent(final String string)
    {
        if (null != string)
        {
            final int size = string.length();

            for (int i = 0; i < size; i++)
            {
                if (string.charAt(i) == 0)
                {
                    throw new IllegalArgumentException("null byte present in string, there are no known legitimate use cases for such data, but several injection attacks may use it.");
                }
            }
        }
        return string;
    }
}
