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

import com.themodernway.common.api.java.util.StringOps
import com.themodernway.common.util.AbstractCommonSpecification

public class StringOpsTestsSpecification extends AbstractCommonSpecification
{
    def setupSpec()
    {
        TestingOps.setupCommonDefault()
    }

    def cleanupSpec()
    {
        TestingOps.closeCommonDefault()
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
        def text = StringOps.toTrimOrElse("", StringOps.toSupplier("text"))

        expect:
        text == "text"
        
        cleanup:
        echo text
    }
    
    def "Test StringOps.toTrimOrElse(null, toSupplier('text'))"()
    {
        setup:
        def text = StringOps.toTrimOrElse(null, StringOps.toSupplier("text"))

        expect:
        text == "text"
        
        cleanup:
        echo text
    }
    
    def "Test StringOps.requireTrimOrNull('')"()
    {
        when:
            StringOps.requireTrimOrNull('')
 
        then:
            thrown NullPointerException
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
}
