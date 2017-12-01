/*
 * Copyright (c) 2017, 2018, The Modern Way. All rights reserved.
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

    def "Test StringOps.isVersionID(null) false"()
    {
        expect:
        StringOps.isVersionID(null) == false
    }

    def "Test StringOps.isVersionID('') false"()
    {
        expect:
        StringOps.isVersionID('') == false
    }

    def "Test StringOps.isVersionID('1') false"()
    {
        expect:
        StringOps.isVersionID("1") == false
    }

    def "Test StringOps.isVersionID('1.0') true"()
    {
        expect:
        StringOps.isVersionID("1.0") == true
    }

    def "Test StringOps.isVersionID('1.0.53') true"()
    {
        expect:
        StringOps.isVersionID("1.0.53") == true
    }

    def "Test StringOps.isVersionID('1.X') false"()
    {
        expect:
        StringOps.isVersionID("1.X") == false
    }

    def "Test StringOps.isVersionID('v1.0') true"()
    {
        expect:
        StringOps.isVersionID("v1.0") == true
    }

    def "Test StringOps.isVersionID('v1') false"()
    {
        expect:
        StringOps.isVersionID("v1") == false
    }

    def "Test StringOps.isVersionID('v12') false"()
    {
        expect:
        StringOps.isVersionID("v12") == false
    }

    def "Test StringOps.isVersionID('v.') false"()
    {
        expect:
        StringOps.isVersionID("v.") == false
    }

    def "Test StringOps.isVersionID('v') false"()
    {
        expect:
        StringOps.isVersionID("v") == false
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
}
