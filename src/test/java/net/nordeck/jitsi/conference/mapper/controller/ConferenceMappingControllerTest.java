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

package net.nordeck.jitsi.conference.mapper.controller;

import net.nordeck.jitsi.conference.mapper.service.ConferenceMappingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConferenceMappingController.class)
class ConferenceMappingControllerTest {

    private static final String MAPPER_PATH = "/conferenceMapper";
    private static final String PARAM_CONFERENCE_NAME = "conference";
    private static final String EMPTY_VALUE = " ";
    private static final String CONFERENCE_NAME = "dummycon@example.com";
    private static final String PARAM_CONFERENCE_ID = "id";
    private static final Long CONFERENCE_ID = 4711L;
    private static final String NULL_VALUE = null;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConferenceMappingService service;

    @Test
    void thatConferenceNameStatusIsOk() throws Exception {
        mockMvc.perform(get(MAPPER_PATH).queryParam(PARAM_CONFERENCE_NAME, CONFERENCE_NAME))
                .andExpect(status().isOk());
    }

    @Test
    void thatConferenceIdStatusIsOk() throws Exception {
        mockMvc.perform(get(MAPPER_PATH).queryParam(PARAM_CONFERENCE_ID, String.valueOf(CONFERENCE_ID)))
                .andExpect(status().isOk());
    }

    @Test
    void thatEmptyConferenceNameIsHandled() throws Exception {
        mockMvc.perform(get(MAPPER_PATH).queryParam(PARAM_CONFERENCE_NAME, EMPTY_VALUE))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void thatNullParameterIsHandled() throws Exception {
        mockMvc.perform(get(MAPPER_PATH).queryParam(PARAM_CONFERENCE_NAME, NULL_VALUE))
                .andExpect(status().isMethodNotAllowed());
        mockMvc.perform(get(MAPPER_PATH).queryParam(PARAM_CONFERENCE_ID, NULL_VALUE))
                .andExpect(status().isMethodNotAllowed());
        mockMvc.perform(get(MAPPER_PATH).queryParam(EMPTY_VALUE, NULL_VALUE))
                .andExpect(status().isMethodNotAllowed());
    }


}
