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

package com.themodernway.common.test

import com.themodernway.common.api.java.util.CommonOps
import com.themodernway.common.api.java.util.StringOps
import com.themodernway.common.api.json.JSONParserException
import com.themodernway.common.api.types.Activatable
import com.themodernway.common.api.types.FixedIterator
import com.themodernway.common.api.types.FixedListIterable
import com.themodernway.common.util.AbstractCommonSpecification

public class CommonTestsSpecification extends AbstractCommonSpecification
{
    def setupSpec()
    {
        setupCommonDefault()
    }

    def cleanupSpec()
    {
        closeCommonDefault()
    }

    def "Test CommonOps.NULL()"()
    {
        setup:
        String valu = CommonOps.NULL()

        expect:
        null == valu
    }

    def "Test CommonOps.toSupplier(int)"()
    {
        setup:
        def valu = CommonOps.toSupplier(10)

        expect:
        valu.getAsInt() == 10

        cleanup:
        echo valu.getAsInt()
    }

    def "Test CommonOps.toSupplier(long)"()
    {
        setup:
        def valu = CommonOps.toSupplier(20L)

        expect:
        valu.getAsLong() == 20L

        cleanup:
        echo valu.getAsLong()
    }

    def "Test CommonOps.toSupplier(double)"()
    {
        setup:
        def valu = CommonOps.toSupplier(30.0d)

        expect:
        valu.getAsDouble() == 30.0d

        cleanup:
        echo valu.getAsDouble()
    }

    def "Test CommonOps.toSupplier(boolean)"()
    {
        setup:
        def valu = CommonOps.toSupplier(true)

        expect:
        valu.getAsBoolean() == true

        cleanup:
        echo valu.getAsBoolean()
    }

    def "Test CommonOps.toSupplier(String)"()
    {
        setup:
        def valu = CommonOps.toSupplier('test')

        expect:
        valu.get() == 'test'

        cleanup:
        echo valu.get()
    }

    def "Test CommonOps.toOptional(String)"()
    {
        setup:
        def valu = CommonOps.toOptional('data')

        expect:
        valu.isPresent() == true
        valu.get() == 'data'
        valu.orElse('else') == 'data'

        cleanup:
        echo valu.get()
    }

    def "Test CommonOps.toOptional(null String)"()
    {
        setup:
        def valu = CommonOps.toOptional(StringOps.NULL_STRING)

        expect:
        valu.isPresent() == false
        valu.orElse('else') == 'else'

        cleanup:
        echo valu.isPresent()
    }

    def "Test CommonOps.toOptional(null String) NoSuchElementException"()
    {
        when:
        CommonOps.toOptional(StringOps.NULL_STRING).get()

        then:
        thrown NoSuchElementException
    }

    def "Test CommonOps.toOptional(null String) JSONParserException"()
    {
        when:
        CommonOps.toOptional(StringOps.NULL_STRING).orElseThrow(CommonOps.toSupplier(new JSONParserException(0)))

        then:
        thrown JSONParserException
    }

    def "Test CommonOps.toList()"()
    {
        setup:
        def list = CommonOps.toList(['A', 'B', 'C', 'A'])

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(Arrays.stream())"()
    {
        setup:
        def list = CommonOps.toList(Arrays.stream('A', 'B', 'C', 'A'))

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(Arrays.stream().distinct())"()
    {
        setup:
        def list = CommonOps.toList(Arrays.stream('A', 'B', 'C', 'A').distinct())

        expect:
        list.size() == 3

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(Collection).values()"()
    {
        setup:
        def list = CommonOps.toList([name: 'dean', age: '53', mood: 'good', life: 'good'].values())

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(Collection).values().stream().distinct()"()
    {
        setup:
        def list = CommonOps.toList([name: 'dean', age: '53', mood: 'good', life: 'good'].values().stream().distinct())

        expect:
        list.size() == 3

        cleanup:
        echo list
    }

    def "Test CommonOps.toKeys(Collection)"()
    {
        setup:
        def list = CommonOps.toKeys([name: 'dean', age: '53', mood: 'good', life: 'good'])

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(FixedListIterable)"()
    {
        setup:
        def iter = new FixedListIterable<String>('A', 'B', 'C', 'A')
        def list = CommonOps.toList(iter)

        expect:
        list.size() == 4
        iter.size() == 4
        list.isEmpty() == false
        iter.isEmpty() == false

        cleanup:
        echo list
        echo iter
    }

    def "Test FixedIterator NoSuchElementException"()
    {
        setup:
        def iter = new FixedIterator<String>(new FixedListIterable<String>('A'))
        while (iter.hasNext())
        {
            iter.next()
        }

        when:
        iter.next()

        then:
        thrown NoSuchElementException
    }

    def "Test CommonOps.toList(FixedListIterable.stream().sorted().distinct()"()
    {
        setup:
        def list = CommonOps.toList(new FixedListIterable<String>('Z', 'C', 'B', 'A', 'Z', 'A').stream().sorted().distinct())

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test Activatable()"()
    {
        setup:
        def valu = new Activatable()

        expect:
        valu.isActive() == false

        cleanup:
        echo valu.isActive()
    }

    def "Test Activatable(false)"()
    {
        setup:
        def valu = new Activatable(false)

        expect:
        valu.isActive() == false

        cleanup:
        echo valu.isActive()
    }

    def "Test Activatable(true)"()
    {
        setup:
        def valu = new Activatable(true)

        expect:
        valu.isActive() == true

        cleanup:
        echo valu.isActive()
    }

    def "Test Activatable().setActive()"()
    {
        setup:
        def valu = new Activatable()

        expect:
        valu.isActive() == false
        valu.setActive(false) == false
        valu.setActive(true) == true
        valu.isActive() == true

        cleanup:
        echo valu.isActive()
    }

    def "Test Activatable(false).setActive()"()
    {
        setup:
        def valu = new Activatable(false)

        expect:
        valu.isActive() == false
        valu.setActive(false) == false
        valu.setActive(true) == true
        valu.isActive() == true

        cleanup:
        echo valu.isActive()
    }

    def "Test Activatable(true).setActive()"()
    {
        setup:
        def valu = new Activatable(true)

        expect:
        valu.isActive() == true
        valu.setActive(true) == false
        valu.setActive(false) == true
        valu.isActive() == false

        cleanup:
        echo valu.isActive()
    }
}
