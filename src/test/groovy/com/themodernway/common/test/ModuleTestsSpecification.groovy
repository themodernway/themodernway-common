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
import com.themodernway.common.util.TestModule

import spock.lang.Shared

public class ModuleTestsSpecification extends AbstractCommonSpecification
{
    @Shared test = new TestModule()

    def "Test TestModule.open 0()"()
    {
        expect:
        test.isActive() == true
        test.getValue() == 0

        cleanup:
        echo test
        echo test.getValue()
    }

    def "Test TestModule.open 1()"()
    {
        setup:
        test.refresh()

        expect:
        test.isActive() == true
        test.getValue() == 1

        cleanup:
        echo test
        echo test.getValue()
    }

    def "Test TestModule.close 2()"()
    {
        setup:
        test.refresh()
        test.close()
        test.refresh()

        expect:
        test.isActive() == test.isOpen()
        test.getValue() == 2

        cleanup:
        echo test
        echo test.getValue()
    }
}
