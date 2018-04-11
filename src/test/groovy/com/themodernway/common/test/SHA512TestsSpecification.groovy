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

import com.themodernway.common.util.AbstractCommonSpecification
import com.themodernway.common.util.SHA512Helper

import spock.lang.Shared

public class SHA512TestsSpecification extends AbstractCommonSpecification
{
    @Shared helper = new SHA512Helper()

    def "Test SHA512Helper.test 0()"()
    {
        expect:
        helper.sha512("dean")  == helper.sha512("dean")
    }

    def "Test SHA512Helper.test 1()"()
    {
        expect:
        helper.sha512("dean", "test", 17)  == helper.sha512("dean", "test", 17)
    }

    def "Test SHA512Helper.test 2()"()
    {
        expect:
        helper.sha512("dean", "test")  == helper.sha512("dean", "test")
    }

    def "Test SHA512Helper.test 3()"()
    {
        expect:
        helper.sha512("dean", "test", 1)  == helper.sha512("dean", "test", 1)
    }

    def "Test SHA512Helper.test 4()"()
    {
        expect:
        helper.sha512("dean", "test", 3)  == helper.sha512("dean", "test", 3)
    }

    def "Test SHA512Helper.test 5()"()
    {
        expect:
        helper.sha512("dean", "test", 30)  == helper.sha512("dean", "test", 30)
    }

    def "Test SHA512Helper.test 6()"()
    {
        expect:
        helper.sha512("dean", 30)  == helper.sha512("dean", 30)
    }
}
