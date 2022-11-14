/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package net.nordeck.jitsi.conference.mapper.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConferenceNameFilterTest {

    private final static String NON_FORMATTED_CONFERENCE_NAME = "*Open§ Sesame :123<&$";
    private final static String FORMATTED_CONFERENCE_NAME = "open-sesame-123";

    @Test
    public void thatConferenceNameIsFormatted() {
        ConferenceNameFilter filter = new ConferenceNameFilter();
        assertEquals(FORMATTED_CONFERENCE_NAME, filter.adaptConferenceName(filter.adaptConferenceName(NON_FORMATTED_CONFERENCE_NAME)));
    }
}
