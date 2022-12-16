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

package net.nordeck.jitsi.conference.mapper.service;

import net.nordeck.jitsi.conference.mapper.db.ConferenceMappingRepository;
import net.nordeck.jitsi.conference.mapper.db.entities.ConferenceMapping;
import net.nordeck.jitsi.conference.mapper.dto.NotFoundResponseDto;
import net.nordeck.jitsi.conference.mapper.dto.ValidResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConferenceMappingServiceTest {

    private static final String CONFERENCE_NAME = "test-conference";
    private static final Long CONFERENCE_ID = 23L;
    private static final Long CONFERENCE_ID_NON_MATCHING = 8L;
    private final static String VALID_RESPONSE = "Successfully retrieved conference mapping";
    private final static String NOT_FOUND_RESPONSE = "No conference mapping was found";
    private final static String NON_PROVIDED_RESPONSE = "No conference or id provided";

    @Mock
    private ConferenceMappingRepository repo;
    @InjectMocks
    private ConferenceMappingService service;

    private ConferenceMapping entity;

    @BeforeEach
    public void setUp() {
        this.entity = new ConferenceMapping();
        entity.setConferenceId(CONFERENCE_ID);
        entity.setConferenceName(CONFERENCE_NAME);
    }

    @Test
    public void thatConferenceIsCreated() {
        when(repo.findConferenceMappingByConferenceName(CONFERENCE_NAME)).thenReturn(entity);

        assertThat(service.getConferenceMappingByConferenceName(CONFERENCE_NAME)).isNotNull();
        assertEquals(VALID_RESPONSE, service.getConferenceMappingByConferenceName(CONFERENCE_NAME).getMessage());
        assertThat(service.getConferenceMappingByConferenceName(CONFERENCE_NAME).getClass()).isEqualTo(ValidResponseDto.class);
    }

    @Test
    public void thatConferenceIsFound() {
        when(repo.findConferenceMappingByConferenceName(CONFERENCE_NAME)).thenReturn(entity);

        assertThat(service.getConferenceMappingByConferenceName(CONFERENCE_NAME)).isNotNull();
        assertEquals(VALID_RESPONSE, service.getConferenceMappingByConferenceName(CONFERENCE_NAME).getMessage());
        assertThat(service.getConferenceMappingByConferenceName(CONFERENCE_NAME).getClass()).isEqualTo(ValidResponseDto.class);
        assertThat(service.getConferenceMappingByConferenceName(CONFERENCE_NAME).getConference().equals(entity.getConferenceName()));
    }

    @Test
    public void thatConferenceNameIsReturnedByConferenceId() {
        when(repo.findConferenceMappingByConferenceId(CONFERENCE_ID)).thenReturn(entity);
        service.createConferenceMapping(CONFERENCE_NAME);

        assertThat(service.getConferenceMappingByConferenceId(CONFERENCE_ID)).isNotNull();
        assertThat(service.getConferenceMappingByConferenceId(CONFERENCE_ID).getMessage()).isEqualTo(VALID_RESPONSE);
        assertThat(service.getConferenceMappingByConferenceId(CONFERENCE_ID).getClass()).isEqualTo(ValidResponseDto.class);
    }

    @Test
    public void thatNotFoundMessageIsReturnedByUnknownID() {
        assertThat(service.getConferenceMappingByConferenceId(CONFERENCE_ID_NON_MATCHING)).isNotNull();
        assertEquals(NOT_FOUND_RESPONSE, service.getConferenceMappingByConferenceId(CONFERENCE_ID_NON_MATCHING).getMessage());
        assertThat(service.getConferenceMappingByConferenceId(CONFERENCE_ID).getClass()).isEqualTo(NotFoundResponseDto.class);
    }

    @Test
    public void thatNonProvidedMessageIsReturned() {
        assertThat(service.toNonProvidedRequestDto()).isNotNull();
        assertEquals(NON_PROVIDED_RESPONSE, service.toNonProvidedRequestDto().getMessage());
        assertEquals(false, service.toNonProvidedRequestDto().getConference());
        assertEquals(false, service.toNonProvidedRequestDto().getId());
    }

}
