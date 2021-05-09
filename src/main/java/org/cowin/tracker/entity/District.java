package org.cowin.tracker.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class District {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("district_id")
    private Long districtId;

    private Long stateId;

    private String stateName;

    @JsonProperty("district_name")
    private String districtName;
}
