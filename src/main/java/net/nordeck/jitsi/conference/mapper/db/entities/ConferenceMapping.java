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

package net.nordeck.jitsi.conference.mapper.db.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = ConferenceMapping.TABLE_NAME)
public class ConferenceMapping {
    public static final String TABLE_NAME = "conference_mapping";
    public static final String COLUMN_ID_NAME = "id";
    public static final String COLUMN_CONFERENCE_ID_NAME = "conference_id";
    public static final String COLUMN_CONFERENCE_NAME_NAME = "conference_name";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conference_mapping_id_seq")
    @SequenceGenerator(name = "conference_mapping_id_seq", sequenceName = "conference_mapping_id_seq", allocationSize = 1)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private Long id;
    @Column(name = COLUMN_CONFERENCE_ID_NAME, nullable = false, unique = true)
    private Long conferenceId;
    @Column(name = COLUMN_CONFERENCE_NAME_NAME, nullable = false, unique = true)
    private String conferenceName;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Long getConferenceId() {return conferenceId;}

    public void setConferenceId(Long conferenceId) {this.conferenceId = conferenceId;}

    public String getConferenceName() {return conferenceName;}

    public void setConferenceName(String conferenceName) {this.conferenceName = conferenceName;}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ConferenceMapping that)) return false;
        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
}