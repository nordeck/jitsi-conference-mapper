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


import net.nordeck.jitsi.conference.mapper.dto.ResponseDto;
import net.nordeck.jitsi.conference.mapper.service.ConferenceMappingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceMappingController {

    private final ConferenceMappingService service;

    @Autowired
    public ConferenceMappingController(ConferenceMappingService service) {
        this.service = service;
    }

    @GetMapping("/conferenceMapper")
    public ResponseEntity<ResponseDto> mapConference(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String conference) {

        if (StringUtils.isBlank(conference) && id == null) {
            return ResponseEntity.status(405).body(service.toNonProvidedRequestDto());
        }
        if (StringUtils.isBlank(conference) && id != null) {
            return ResponseEntity.ok().body(service.getConferenceMappingByConferenceId(id));
        } else {
            return ResponseEntity.ok().body(service.getConferenceMappingByConferenceName(conference));
        }
    }
}



