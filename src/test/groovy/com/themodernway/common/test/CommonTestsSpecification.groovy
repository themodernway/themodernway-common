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
import com.themodernway.common.api.json.JSONParserException
import com.themodernway.common.api.types.Activatable
import com.themodernway.common.api.types.CoreCursor
import com.themodernway.common.api.types.FixedListIterable
import com.themodernway.common.util.AbstractCommonSpecification

import groovy.transform.CompileStatic

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
        def list = CommonOps.toList('A', 'B', 'C', 'D')

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList([])"()
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

    def "Test CommonOps.toList(Map).values()"()
    {
        setup:
        def list = CommonOps.toList([name: 'dean', age: '53', mood: 'good', life: 'good'].values())

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(Map).keySet()"()
    {
        setup:
        def list = CommonOps.toList([name: 'dean', age: '53', mood: 'good', life: 'good'].keySet())

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

    def "Test CommonOps.toList(Enumeration)"()
    {
        setup:
        def list = CommonOps.toList(new Hashtable<String, String>([name: 'dean', age: '53', mood: 'good', life: 'good']).keys())

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(FixedListIterable)"()
    {
        setup:
        def list = CommonOps.toList(new FixedListIterable<String>('A', 'B', 'C', 'D'))

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(FixedListIterable[])"()
    {
        setup:
        def list = CommonOps.toList(new FixedListIterable<String>(['A', 'B', 'C', 'D']))

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(FixedCursor)"()
    {
        setup:
        def list = CommonOps.toList(new CoreCursor<String>('A', 'B', 'C', 'D'))

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toList(FixedCursor[])"()
    {
        setup:
        def list = CommonOps.toList(new CoreCursor<String>(['A', 'B', 'C', 'D']))

        expect:
        list.size() == 4

        cleanup:
        echo list
    }

    def "Test CommonOps.toKeys(Map)"()
    {
        setup:
        def list = CommonOps.toKeys([name: 'dean', age: '53', mood: 'good', life: 'good'])

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

    def "Test emptyMap()"()
    {
        setup:
        def valu = CommonOps.emptyMap()

        expect:
        valu.size() == 0
        valu.isEmpty() == true

        cleanup:
        echo valu.isEmpty()
    }

    def "Test emptyList()"()
    {
        setup:
        def valu = CommonOps.emptyList()

        expect:
        valu.size() == 0
        valu.isEmpty() == true

        cleanup:
        echo valu.isEmpty()
    }

    def "Test arrayListOfSize(0)"()
    {
        setup:
        def valu = CommonOps.arrayListOfSize(0)
        valu << "dean"

        expect:
        valu.size() == 1
        valu.isEmpty() == false

        cleanup:
        echo valu
    }

    def "Test arrayListOfSize(IS_NOT_FOUND)"()
    {
        setup:
        def valu = CommonOps.arrayListOfSize(CommonOps.IS_NOT_FOUND)
        valu << "dean"

        expect:
        valu.size() == 1
        valu.isEmpty() == false

        cleanup:
        echo valu
    }

    def "Test arrayList()"()
    {
        setup:
        def valu = CommonOps.arrayList()
        valu << "dean"

        expect:
        valu.size() == 1
        valu.isEmpty() == false

        cleanup:
        echo valu
    }

    def "Test arrayList(...)"()
    {
        setup:
        def valu = CommonOps.arrayList('A', 'B', 'C', 'D')

        expect:
        valu.size() == 4

        cleanup:
        echo valu
    }

    def "Test arrayList(Stream)"()
    {
        setup:
        def valu = CommonOps.arrayList(Arrays.stream('A', 'B', 'C', 'A').distinct())

        expect:
        valu.size() == 3

        cleanup:
        echo valu
    }

    def "Test toUnmodifiableList(...)"()
    {
        setup:
        def valu = CommonOps.toUnmodifiableList('A', 'B', 'C', 'D')

        expect:
        valu.size() == 4

        cleanup:
        echo valu
    }

    def "Test toUnmodifiableList([])"()
    {
        setup:
        def valu = CommonOps.toUnmodifiableList(['A', 'B', 'C', 'D'])

        expect:
        valu.size() == 4

        cleanup:
        echo valu
    }

    def "Test linkedMap()"()
    {
        setup:
        def valu = CommonOps.linkedMap()
        valu['dean'] = "test"

        expect:
        valu.size() == 1

        cleanup:
        echo valu
    }

    def "Test linkedMap([:])"()
    {
        setup:
        def valu = CommonOps.linkedMap([dean: 'test'])

        expect:
        valu.size() == 1

        cleanup:
        echo valu
    }

    def "Test toUnmodifiableMap([:])"()
    {
        setup:
        def valu = CommonOps.toUnmodifiableMap([dean: 'test'])

        expect:
        valu.size() == 1

        cleanup:
        echo valu
    }

    def "Test toUnmodifiableMap([:]) fail"()
    {
        when:
        CommonOps.toUnmodifiableMap([dean: 'test']).put('data', 'oops')

        then:
        thrown UnsupportedOperationException
    }

    def "Test isNull()"()
    {
        setup:
        def valu = "dean"

        expect:
        CommonOps.isNull(null) == true
        CommonOps.isNull(valu) == false

        cleanup:
        echo valu
    }

    def "Test isNonNull()"()
    {
        setup:
        def valu = "dean"

        expect:
        CommonOps.isNonNull(valu) == true
        CommonOps.isNonNull(null) == false

        cleanup:
        echo valu
    }

    def "Test requireNonNullOrElse()"()
    {
        setup:
        def valu = "dean"

        expect:
        CommonOps.requireNonNullOrElse(valu, "test") == "dean"
        CommonOps.requireNonNullOrElse(null, "test") == "test"

        cleanup:
        echo valu
    }

    def "Test requireNonNullOrElse(Supplier)"()
    {
        setup:
        def valu = "dean"

        expect:
        CommonOps.requireNonNullOrElse(valu, CommonOps.toSupplier("test")) == "dean"
        CommonOps.requireNonNullOrElse(null, CommonOps.toSupplier("test")) == "test"

        cleanup:
        echo valu
    }

    @CompileStatic
    def "Test CAST()"()
    {
        setup:
        def valu = CommonOps.CAST(null)

        expect:
        valu == null
    }

    @CompileStatic
    def "Test CAST(String, String)"()
    {
        setup:
        String valu = CommonOps.CAST("test")

        expect:
        valu == "test"

        cleanup:
        echo valu
    }

    @CompileStatic
    def "Test CAST(Integer)"()
    {
        setup:
        Integer valu = CommonOps.CAST(new Integer(50))

        expect:
        valu == 50

        cleanup:
        echo valu
    }

    def "Test STRMAP(RAWMAP)"()
    {
        setup:
        Map<String, Object> valu = CommonOps.STRMAP(CommonOps.RAWMAP([:]))

        expect:
        valu.isEmpty() == true

        cleanup:
        echo valu
    }

    def "Test all(['A', 'B', 'C', 'D'], ['A', 'B', 'C', 'D'])"()
    {
        setup:
        def valu = CommonOps.all(['A', 'B', 'C', 'D'], ['A', 'B', 'C', 'D'])

        expect:
        valu == true

        cleanup:
        echo valu
    }

    def "Test all(['A', 'B', 'C', 'D'], ['A', 'B'])"()
    {
        setup:
        def valu = CommonOps.all(['A', 'B', 'C', 'D'], ['A', 'B'])

        expect:
        valu == true

        cleanup:
        echo valu
    }

    def "Test all(['A', 'B'], ['B', 'A'])"()
    {
        setup:
        def valu = CommonOps.all(['A', 'B'], ['B', 'A'])

        expect:
        valu == true

        cleanup:
        echo valu
    }

    def "Test all(['A'], ['A'])"()
    {
        setup:
        def valu = CommonOps.all(['A'], ['A'])

        expect:
        valu == true

        cleanup:
        echo valu
    }

    def "Test all(['A'], ['B'])"()
    {
        setup:
        def valu = CommonOps.all(['A'], ['B'])

        expect:
        valu == false

        cleanup:
        echo valu
    }

    def "Test all(['A', 'B'], ['A', 'B', 'C'])"()
    {
        setup:
        def valu = CommonOps.all(['A', 'B'], ['A', 'B', 'C'])

        expect:
        valu == false

        cleanup:
        echo valu
    }

    def "Test any(['A', 'B', 'C', 'D'], ['A', 'B', 'C', 'D'])"()
    {
        setup:
        def valu = CommonOps.any(['A', 'B', 'C', 'D'], ['A', 'B', 'C', 'D'])

        expect:
        valu == true

        cleanup:
        echo valu
    }

    def "Test any(['A', 'B', 'C', 'D'], ['A', 'B'])"()
    {
        setup:
        def valu = CommonOps.any(['A', 'B', 'C', 'D'], ['A', 'B'])

        expect:
        valu == true

        cleanup:
        echo valu
    }

    def "Test any(['A', 'B', 'C', 'D'], ['E', 'F'])"()
    {
        setup:
        def valu = CommonOps.any(['A', 'B', 'C', 'D'], ['E', 'F'])

        expect:
        valu == false

        cleanup:
        echo valu
    }

    def "Test none(['A', 'B', 'C', 'D'], ['A', 'B', 'C', 'D'])"()
    {
        setup:
        def valu = CommonOps.none(['A', 'B', 'C', 'D'], ['A', 'B', 'C', 'D'])

        expect:
        valu == false

        cleanup:
        echo valu
    }

    def "Test none(['A', 'B', 'C', 'D'], ['A', 'B'])"()
    {
        setup:
        def valu = CommonOps.none(['A', 'B', 'C', 'D'], ['A', 'B'])

        expect:
        valu == false

        cleanup:
        echo valu
    }

    def "Test none(['A', 'B', 'C', 'D'], ['E', 'F'])"()
    {
        setup:
        def valu = CommonOps.none(['A', 'B', 'C', 'D'], ['E', 'F'])

        expect:
        valu == true

        cleanup:
        echo valu
    }
}
