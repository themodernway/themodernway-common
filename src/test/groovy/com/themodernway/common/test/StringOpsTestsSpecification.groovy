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

package com.themodernway.common.test

import com.themodernway.common.api.java.util.CommonOps
import com.themodernway.common.api.java.util.StringOps
import com.themodernway.common.util.AbstractCommonSpecification
import com.themodernway.common.util.StringOpsHelper

public class StringOpsTestsSpecification extends AbstractCommonSpecification
{
    def setupSpec()
    {
        setupCommonDefault()
    }

    def cleanupSpec()
    {
        closeCommonDefault()
    }

    def "Test StringOps.test()'"()
    {
        expect:
        StringOps.test() == true
    }

    def "Test StringOps.repeat(' ', 0)'"()
    {
        expect:
        StringOps.repeat(" ", 0) == " "
    }

    def "Test StringOps.repeat(' ', 1)'"()
    {
        expect:
        StringOps.repeat(" ", 1) == " "
    }

    def "Test StringOps.repeat('', 10)'"()
    {
        expect:
        StringOps.repeat("", 10) == ""
    }

    def "Test StringOps.repeat(null, 10)'"()
    {
        expect:
        StringOps.repeat(null, 10) == null
    }

    def "Test StringOps.repeat(' ', 4)'"()
    {
        expect:
        StringOps.repeat(" ", 4) == "    "
    }

    def "Test StringOps.reverse('1234') == '4321'"()
    {
        expect:
        StringOps.reverse("1234") == "4321"
    }

    def "Test StringOps.reverse('A') == 'A'"()
    {
        expect:
        StringOps.reverse("A") == "A"
    }

    def "Test StringOps.reverse(null) == null"()
    {
        expect:
        StringOps.reverse(null) == null
    }

    def "Test StringOps.isDigits('1234') true"()
    {
        expect:
        StringOps.isDigits("1234") == true
    }

    def "Test StringOps.isDigits('text') false"()
    {
        expect:
        StringOps.isDigits("text") == false
    }

    def "Test StringOps.isDigits('') false"()
    {
        expect:
        StringOps.isDigits("") == false
    }

    def "Test StringOps.isDigits(null) false"()
    {
        expect:
        StringOps.isDigits(null) == false
    }

    def "Test StringOps.isAlpha('1234') false"()
    {
        expect:
        StringOps.isAlpha("1234") == false
    }

    def "Test StringOps.isAlpha('text') true"()
    {
        expect:
        StringOps.isAlpha("text") == true
    }

    def "Test StringOps.isAlpha('') false"()
    {
        expect:
        StringOps.isAlpha("") == false
    }

    def "Test StringOps.isAlpha(null) false"()
    {
        expect:
        StringOps.isAlpha(null) == false
    }

    def "Test StringOps.isAlphaOrDigits(null) false"()
    {
        expect:
        StringOps.isAlphaOrDigits(null) == false
    }

    def "Test StringOps.isAlphaOrDigits('') false"()
    {
        expect:
        StringOps.isAlphaOrDigits("") == false
    }

    def "Test StringOps.isAlphaOrDigits('1234') true"()
    {
        expect:
        StringOps.isAlphaOrDigits("1234") == true
    }

    def "Test StringOps.isAlphaOrDigits('text') true"()
    {
        expect:
        StringOps.isAlphaOrDigits("text") == true
    }

    def "Test StringOps.isAlphaOrDigits('1234text') true"()
    {
        expect:
        StringOps.isAlphaOrDigits("1234text") == true
    }

    def "Test StringOps.isAlphaOrDigits('_') false"()
    {
        expect:
        StringOps.isAlphaOrDigits("_") == false
    }

    def "Test StringOps.isAlphaOrDigitsStartsAlpha(null) false"()
    {
        expect:
        StringOps.isAlphaOrDigitsStartsAlpha(null) == false
    }

    def "Test StringOps.isAlphaOrDigitsStartsAlpha('') false"()
    {
        expect:
        StringOps.isAlphaOrDigitsStartsAlpha("") == false
    }

    def "Test StringOps.isAlphaOrDigitsStartsAlpha('text1234') true"()
    {
        expect:
        StringOps.isAlphaOrDigitsStartsAlpha("text1234") == true
    }

    def "Test StringOps.isAlphaOrDigitsStartsAlpha('1234text') false"()
    {
        expect:
        StringOps.isAlphaOrDigitsStartsAlpha("1234text") == false
    }

    def "Test StringOps.isAlphaOrDigitsStartsAlpha('text1234_') false"()
    {
        expect:
        StringOps.isAlphaOrDigitsStartsAlpha("text1234_") == false
    }

    def "Test StringOps.failIfNullBytePresent()"()
    {
        when:
        StringOps.failIfNullBytePresent("\0")

        then:
        thrown IllegalArgumentException
    }

    def "Test StringOps.failIfNullBytePresent(true)"()
    {
        when:
        StringOps.failIfNullBytePresent("dean\0")

        then:
        thrown IllegalArgumentException
    }

    def "Test StringOps.failIfNullBytePresent(false)"()
    {
        when:
        StringOps.failIfNullBytePresent("dean")

        then:
        notThrown IllegalArgumentException
    }

    def "Test StringOps.failIfNullBytePresent(null)"()
    {
        when:
        StringOps.failIfNullBytePresent(null)

        then:
        notThrown IllegalArgumentException
    }

    def "Test StringOps.toTrimOrNull('text')"()
    {
        setup:
        def text = StringOps.toTrimOrNull("text")

        expect:
        text == "text"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrNull(' text ')"()
    {
        setup:
        def text = StringOps.toTrimOrNull(" text ")

        expect:
        text == "text"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrNull(null)"()
    {
        setup:
        def text = StringOps.toTrimOrNull(null)

        expect:
        text == null

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrNull('')"()
    {
        setup:
        def text = StringOps.toTrimOrNull("")

        expect:
        text == null

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrElse('', 'text')"()
    {
        setup:
        def text = StringOps.toTrimOrElse("", "text")

        expect:
        text == "text"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrElse('data', 'text')"()
    {
        setup:
        def text = StringOps.toTrimOrElse("data", "text")

        expect:
        text == "data"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrElse(null, 'text')"()
    {
        setup:
        def text = StringOps.toTrimOrElse(null, "text")

        expect:
        text == "text"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrElse('', toSupplier('text'))"()
    {
        setup:
        def text = StringOps.toTrimOrElse("", CommonOps.toSupplier("text"))

        expect:
        text == "text"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrElse('data', toSupplier('text'))"()
    {
        setup:
        def text = StringOps.toTrimOrElse("data", CommonOps.toSupplier("text"))

        expect:
        text == "data"

        cleanup:
        echo text
    }

    def "Test StringOps.toTrimOrElse(null, toSupplier('text'))"()
    {
        setup:
        def text = StringOps.toTrimOrElse(null, CommonOps.toSupplier("text"))

        expect:
        text == "text"

        cleanup:
        echo text
    }

    def "Test StringOps.requireTrimOrNull('')"()
    {
        when:
        StringOps.requireTrimOrNull("")

        then:
        thrown NullPointerException
    }

    def "Test StringOps.requireTrimOrNull('', 'oops')"()
    {
        when:
        StringOps.requireTrimOrNull("", "oops")

        then:
        thrown NullPointerException
    }

    def "Test StringOps.requireTrimOrNull('text', 'oops')"()
    {
        when:
        StringOps.requireTrimOrNull("text", "oops")

        then:
        notThrown NullPointerException
    }

    def "Test StringOps.requireTrimOrNull('', toSupplier('oops'))"()
    {
        when:
        StringOps.requireTrimOrNull("", CommonOps.toSupplier("oops"))

        then:
        thrown NullPointerException
    }

    def "Test StringOps.requireTrimOrNull('text', toSupplier('oops'))"()
    {
        when:
        StringOps.requireTrimOrNull("text", CommonOps.toSupplier("oops"))

        then:
        notThrown NullPointerException
    }

    def "Test StringOps.requireTrimOrNull(null)"()
    {
        when:
        StringOps.requireTrimOrNull(null)

        then:
        thrown NullPointerException
    }

    def "Test StringOps.requireTrimOrNull('text')"()
    {
        when:
        StringOps.requireTrimOrNull("text")

        then:
        notThrown NullPointerException
    }

    def "Test StringOps.toArray(['text'])"()
    {
        setup:
        def list = StringOps.toArray(["text"])

        expect:
        list.length == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toArray(['text','data'])"()
    {
        setup:
        def list = StringOps.toArray(["text", "data"])

        expect:
        list.length == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.toArray('text')"()
    {
        setup:
        def list = StringOps.toArray("text")

        expect:
        list.length == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toArray('text','data')"()
    {
        setup:
        def list = StringOps.toArray("text", "data")

        expect:
        list.length == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.toUnique(['text','text'])"()
    {
        setup:
        def list = StringOps.toUnique(["text", "text"])

        expect:
        list.size() == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUnique(['text','text',null])"()
    {
        setup:
        def list = StringOps.toUnique(["text", "text", null])

        expect:
        list.size() == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueArray(['text'])"()
    {
        setup:
        def list = StringOps.toUniqueArray(["text"])

        expect:
        list.length == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueArray(['text','text'])"()
    {
        setup:
        def list = StringOps.toUniqueArray(["text", "text"])

        expect:
        list.length == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueArray(['text','data','text'])"()
    {
        setup:
        def list = StringOps.toUniqueArray(["text", "data", "text"])

        expect:
        list.length == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueArray('text','text')"()
    {
        setup:
        def list = StringOps.toUniqueArray("text", "text")

        expect:
        list.length == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueArray('text','data','text')"()
    {
        setup:
        def list = StringOps.toUniqueArray("text", "data", "text")

        expect:
        list.length == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueStringList('text')"()
    {
        setup:
        def list = StringOps.toUniqueTokenStringList("text")

        expect:
        list.size() == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueStringList('text,text')"()
    {
        setup:
        def list = StringOps.toUniqueTokenStringList("text, text")

        expect:
        list.size() == 1
        list[0] == "text"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueStringList('text,data,text')"()
    {
        setup:
        def list = StringOps.toUniqueTokenStringList("text, data, text")

        expect:
        list.size() == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.tokenizeToStringCollection(null)"()
    {
        setup:
        def list = StringOps.tokenizeToStringCollection(null)

        expect:
        list.size() == 0

        cleanup:
        echo list
    }

    def "Test StringOps.tokenizeToStringCollection('')"()
    {
        setup:
        def list = StringOps.tokenizeToStringCollection('')

        expect:
        list.size() == 0

        cleanup:
        echo list
    }

    def "Test StringOps.tokenizeToStringCollection('text, data, dean')"()
    {
        setup:
        def list = StringOps.tokenizeToStringCollection("text, data, dean")

        expect:
        list.size() == 3
        list[0] == "text"
        list[1] == "data"
        list[2] == "dean"

        cleanup:
        echo list
    }

    def "Test StringOps.tokenizeToStringCollection('text: data: dean', ':')"()
    {
        setup:
        def list = StringOps.tokenizeToStringCollection("text: data: dean", ":")

        expect:
        list.size() == 3
        list[0] == "text"
        list[1] == "data"
        list[2] == "dean"

        cleanup:
        echo list
    }

    def "Test StringOps.tokenizeToStringCollection('text: data: dean:', ':')"()
    {
        setup:
        def list = StringOps.tokenizeToStringCollection("text: data: dean:", ":")

        expect:
        list.size() == 3
        list[0] == "text"
        list[1] == "data"
        list[2] == "dean"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueStringList(['text','data','text'])"()
    {
        setup:
        def list = StringOps.toUnique(["text", "data", "text"])

        expect:
        list.size() == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.toUniqueStringList((StringOps.toArray(['text','data','text']))"()
    {
        setup:
        def list = StringOps.toUnique(StringOps.toArray(["text", "data", "text"]))

        expect:
        list.size() == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOps.toCommaSeparated(['text','data','text'])"()
    {
        setup:
        def list = StringOps.toCommaSeparated(["text", "data", "text"])

        expect:
        list != null
        list == "text, data, text"

        cleanup:
        echo list
    }

    def "Test StringOps.toCommaSeparated('text','data','text')"()
    {
        setup:
        def list = StringOps.toCommaSeparated("text", "data", "text")

        expect:
        list != null
        list == "text, data, text"

        cleanup:
        echo list
    }

    def "Test StringOps.toEscapedForJavaScript()"()
    {
        when:
        StringOps.toEscapedForJavaScript("dean", null as StringBuilder)

        then:
        thrown IllegalArgumentException
    }

    def "Test StringOps.toEscapedStringForJavaScript(dean)"()
    {
        setup:
        def text = StringOps.toEscapedStringForJavaScript("dean")

        expect:
        text != null
        text == '"dean"'

        cleanup:
        echo text
    }

    def "Test StringOps.toEscapedStringForJavaScript(null)"()
    {
        setup:
        def text = StringOps.toEscapedStringForJavaScript(null)

        expect:
        text != null
        text == 'null'

        cleanup:
        echo text
    }

    def "Test StringOps.toEscapedForJavaScriptAppendable(null)"()
    {
        setup:
        def text = StringOps.toEscapedForJavaScriptAppendable(null, new StringBuilder()).toString()

        expect:
        text != null
        text == 'null'

        cleanup:
        echo text
    }

    def "Test StringOps.toPrintableString(['text','data','text'])"()
    {
        setup:
        def list = StringOps.toPrintableString(["text", "data", "text"])

        expect:
        list != null
        list == '["text", "data", "text"]'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString(['text', 'data', 'text', null])"()
    {
        setup:
        def list = StringOps.toPrintableString(["text", "data", "text", null])

        expect:
        list != null
        list == '["text", "data", "text", null]'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString('text','data','text')"()
    {
        setup:
        def list = StringOps.toPrintableString("text", "data", "text\u00A9")

        expect:
        list != null
        list == '["text", "data", "text\\u00A9"]'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString(null)"()
    {
        setup:
        def temp = []
        def list = StringOps.toPrintableString(temp = null)

        expect:
        list != null
        list == 'null'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString([])"()
    {
        setup:
        def list = StringOps.toPrintableString([])

        expect:
        list != null
        list == '[]'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString(empty [])"()
    {
        setup:
        def temp = StringOps.toArray([])
        def list = StringOps.toPrintableString(temp)

        expect:
        list != null
        list == '[]'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString(null [])"()
    {
        setup:
        def list = StringOps.toPrintableString(null as String[])

        expect:
        list != null
        list == 'null'

        cleanup:
        echo list
    }

    def "Test StringOps.toPrintableString(['text','data','text']) s"()
    {
        setup:
        def list = StringOps.toPrintableString(["text\n", "data\t", "text\r"])

        expect:
        list != null
        list == '["text\\n", "data\\t", "text\\r"]'

        cleanup:
        echo list
    }

    def "Test StringOpsHelper.getNames('text','data','text')"()
    {
        setup:
        def help = new StringOpsHelper("text", "data", "text")
        def list = help.getNames()

        expect:
        list.size() == 2
        list[0] == "text"
        list[1] == "data"

        cleanup:
        echo list
    }

    def "Test StringOpsHelper.setNames('text','data','text','dean')"()
    {
        setup:
        def help = new StringOpsHelper()
        help.setNames(["text", "data", "text", "dean"])
        def list = help.getNames()

        expect:
        list.size() == 3
        list[0] == "text"
        list[1] == "data"
        list[2] == "dean"

        cleanup:
        echo list
    }

    def "Test StringOpsHelper.setNames([])"()
    {
        setup:
        def help = new StringOpsHelper()
        help.setNames([])
        def list = help.getNames()

        expect:
        list.size() == 0

        cleanup:
        echo list
    }

    def "Test StringOpsHelper.setNames(null)"()
    {
        setup:
        def help = new StringOpsHelper()
        help.setNames(null)
        def list = help.getNames()

        expect:
        list.size() == 0

        cleanup:
        echo list
    }

    def "Test StringOpsHelper.setNamesByTokens('text','data','text','dean')"()
    {
        setup:
        def help = new StringOpsHelper()
        help.setNamesByTokens("text, data, text, dean")
        def list = help.getNames()

        expect:
        list.size() == 3
        list[0] == "text"
        list[1] == "data"
        list[2] == "dean"

        cleanup:
        echo list
    }

    def "Test StringOpsHelper.setNamesByTokens(' ')"()
    {
        setup:
        def help = new StringOpsHelper()
        help.setNamesByTokens(" ")
        def list = help.getNames()

        expect:
        list.size() == 0

        cleanup:
        echo list
    }
}
