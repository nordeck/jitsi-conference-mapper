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
import net.nordeck.jitsi.conference.mapper.dto.InvalidRequestResponseDto;
import net.nordeck.jitsi.conference.mapper.dto.NotFoundResponseDto;
import net.nordeck.jitsi.conference.mapper.dto.ResponseDto;
import net.nordeck.jitsi.conference.mapper.dto.ValidResponseDto;
import net.nordeck.jitsi.conference.mapper.filter.ConferenceNameFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ConferenceMappingService {

    private final static String SUCCESSFUL_MAPPING = "Successfully retrieved conference mapping";
    private final static String NOT_FOUND = "No conference mapping was found";
    private final static String NON_PROVIDED = "No conference or id provided";
    private final ConferenceMappingRepository repository;

    @Autowired
    public ConferenceMappingService(ConferenceMappingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ValidResponseDto createConferenceMapping(String conferenceName) {
        ConferenceNameFilter filter = new ConferenceNameFilter();
        conferenceName = filter.adaptConferenceName(conferenceName);

        ConferenceMapping mappingEntry = new ConferenceMapping();
        mappingEntry.setConferenceName(conferenceName);
        mappingEntry.setConferenceId(repository.getConferenceId());
        repository.save(mappingEntry);

        return toValidResponseDto(mappingEntry);
    }

    public ValidResponseDto getConferenceMappingByConferenceName(String conferenceName) {
        ConferenceMapping mappingEntry = repository.findConferenceMappingByConferenceName(conferenceName);
        return toValidResponseDto(mappingEntry);
    }

    public ResponseDto getConferenceMappingByConferenceId(Long conferenceId) {
        ConferenceMapping mappingEntry = repository.findConferenceMappingByConferenceId(conferenceId);
        if (mappingEntry == null) {
            return toNotFoundResponseDto(conferenceId);
        }
        return toValidResponseDto(mappingEntry);
    }

    public InvalidRequestResponseDto toNonProvidedRequestDto() {
        return new InvalidRequestResponseDto(NON_PROVIDED, false, false);
    }

    private ValidResponseDto toValidResponseDto(ConferenceMapping mappingEntry) {
        return new ValidResponseDto(SUCCESSFUL_MAPPING, mappingEntry.getConferenceId(), mappingEntry.getConferenceName());
    }

    private NotFoundResponseDto toNotFoundResponseDto(Long id) {
        return new NotFoundResponseDto(NOT_FOUND, id, false);
    }
}
