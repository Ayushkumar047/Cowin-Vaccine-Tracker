package org.cowin.tracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.cowin.tracker.entity.Session;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Center {
    @JsonProperty("center_id")
    private Long centerId;
    private String name;
    private String address;
    @JsonProperty("state_name")
    private String stateName;
    @JsonProperty("district_id")
    private Long districtId;
    @JsonProperty("district_name")
    private String districtName;
    @JsonProperty("block_name")
    private String blockName;
    private Long pincode;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("long")
    private Double longitude;
    @JsonProperty("from")
    private String fromTime;
    @JsonProperty("to")
    private String toTime;
    @JsonProperty("fee_type")
    private String feeType;
    private List<Session> sessions;

}
