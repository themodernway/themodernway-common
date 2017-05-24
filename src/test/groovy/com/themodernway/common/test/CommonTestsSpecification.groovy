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

import com.themodernway.common.api.java.util.UUID
import com.themodernway.common.util.AbstractCommonSpecification

import spock.lang.Unroll

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
    
    def "Test UUID isValid() true"()
    {
        setup:
        def uuid = UUID.uuid()

        expect:
        UUID.isValid(uuid) == true
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() length false"()
    {
        setup:
        def uuid = UUID.uuid().substring(2)

        expect:
        UUID.isValid(uuid) == false
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() toLowerCase() false"()
    {
        setup:
        def uuid = UUID.uuid().toLowerCase()

        expect:
        UUID.isValid(uuid) == false
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() toUpperCase() true"()
    {
        setup:
        def uuid = UUID.uuid().toUpperCase()

        expect:
        UUID.isValid(uuid) == true
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() format dash false"()
    {
        setup:
        def uuid = UUID.uuid().replace('-', ' ')

        expect:
        UUID.isValid(uuid) == false
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() format [14]==4 false"()
    {
        setup:
        def chrs = UUID.uuid().toCharArray()
        chrs[14] = 'B'
        def uuid = new String(chrs)

        expect:
        UUID.isValid(uuid) == false
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() format false"()
    {
        setup:
        def chrs = UUID.uuid().toCharArray()
        chrs[0] = 'X'
        def uuid = new String(chrs)

        expect:
        UUID.isValid(uuid) == false
        
        cleanup:
        echo uuid
    }
    
    def "Test UUID isValid() null false"()
    {
        setup:
        def uuid = null

        expect:
        UUID.isValid(uuid) == false
    }
    
    @Unroll
    def "Test UUID isValid() format [#i] false"(int i)
    {
        setup:
        def chrs = UUID.uuid().toCharArray()
        chrs[i] = 'X'
        def uuid = new String(chrs)

        expect:
        UUID.isValid(uuid) == false
        
        cleanup:
        echo uuid

        where:
        i << [8, 13, 14, 18, 23]
    }
}
