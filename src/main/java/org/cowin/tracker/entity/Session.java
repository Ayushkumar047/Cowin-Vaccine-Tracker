package org.cowin.tracker.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.cowin.tracker.model.Center;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "center_id",
        "name",
        "address",
        "state_name",
        "district_name",
        "block_name",
        "pincode",
        "from",
        "to",
        "lat",
        "long",
        "fee_type",
        "session_id",
        "date",
        "available_capacity",
        "fee",
        "min_age_limit",
        "vaccine",
        "slots"
})
@Generated("jsonschema2pojo")
public class Session implements Serializable {
    private final static long serialVersionUID = -3266243541126190099L;

    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("center_id")
    private Long centerId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("state_name")
    private String stateName;
    @JsonProperty("district_id")
    private Long districtId;
    @JsonProperty("district_name")
    private String districtName;
    @JsonProperty("block_name")
    private String blockName;
    @JsonProperty("pincode")
    private Long pincode;
    @JsonProperty("from")
    private String fromTime;
    @JsonProperty("to")
    private String toTime;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("long")
    private Double longitude;
    @JsonProperty("fee_type")
    private String feeType;
    @JsonProperty("session_id")
    private String sessionId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("available_capacity")
    private Long availableCapacity;
    @JsonProperty("fee")
    private String fee;
    @JsonProperty("min_age_limit")
    private Long minAgeLimit;
    @JsonProperty("vaccine")
    private String vaccine;
    @JsonProperty("slots")
    @ElementCollection
    private List<String> slots = new ArrayList<String>();
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public void copyCenter(Center center) {
        this.centerId = center.getCenterId();
        this.districtId = center.getDistrictId();
        this.pincode = center.getPincode();
        this.address = center.getAddress();
        this.blockName = center.getBlockName();
        this.districtName = center.getDistrictName();
        this.feeType = center.getFeeType();
        this.fromTime = center.getFromTime();
        this.toTime = center.getToTime();
        this.latitude = center.getLatitude();
        this.longitude = center.getLongitude();
        this.name = center.getName();
        this.stateName = center.getStateName();
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public Session withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("district_id")
    public Long getDistrictId() {
        return districtId;
    }

    @JsonProperty("district_id")
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Session withDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    @JsonProperty("center_id")
    public Long getCenterId() {
        return centerId;
    }

    @JsonProperty("center_id")
    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public Session withCenterId(Long centerId) {
        this.centerId = centerId;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Session withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    public Session withAddress(String address) {
        this.address = address;
        return this;
    }

    @JsonProperty("state_name")
    public String getStateName() {
        return stateName;
    }

    @JsonProperty("state_name")
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Session withStateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    @JsonProperty("district_name")
    public String getDistrictName() {
        return districtName;
    }

    @JsonProperty("district_name")
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Session withDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    @JsonProperty("block_name")
    public String getBlockName() {
        return blockName;
    }

    @JsonProperty("block_name")
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Session withBlockName(String blockName) {
        this.blockName = blockName;
        return this;
    }

    @JsonProperty("pincode")
    public Long getPincode() {
        return pincode;
    }

    @JsonProperty("pincode")
    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Session withPincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    @JsonProperty("from")
    public String getFromTime() {
        return fromTime;
    }

    @JsonProperty("from")
    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public Session withFromTime(String fromDate) {
        this.fromTime = fromDate;
        return this;
    }

    @JsonProperty("to")
    public String getToTime() {
        return toTime;
    }

    @JsonProperty("to")
    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Session withToTime(String toDate) {
        this.toTime = toDate;
        return this;
    }

    @JsonProperty("lat")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("lat")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Session withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    @JsonProperty("long")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("long")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Session withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    @JsonProperty("fee_type")
    public String getFeeType() {
        return feeType;
    }

    @JsonProperty("fee_type")
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Session withFeeType(String feeType) {
        this.feeType = feeType;
        return this;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("session_id")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Session withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @JsonProperty("date")
    public LocalDate getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Session withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @JsonProperty("available_capacity")
    public Long getAvailableCapacity() {
        return availableCapacity;
    }

    @JsonProperty("available_capacity")
    public void setAvailableCapacity(Long availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Session withAvailableCapacity(Long availableCapacity) {
        this.availableCapacity = availableCapacity;
        return this;
    }

    @JsonProperty("fee")
    public String getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(String fee) {
        this.fee = fee;
    }

    public Session withFee(String fee) {
        this.fee = fee;
        return this;
    }

    @JsonProperty("min_age_limit")
    public Long getMinAgeLimit() {
        return minAgeLimit;
    }

    @JsonProperty("min_age_limit")
    public void setMinAgeLimit(Long minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public Session withMinAgeLimit(Long minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
        return this;
    }

    @JsonProperty("vaccine")
    public String getVaccine() {
        return vaccine;
    }

    @JsonProperty("vaccine")
    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public Session withVaccine(String vaccine) {
        this.vaccine = vaccine;
        return this;
    }

    @JsonProperty("slots")
    public List<String> getSlots() {
        return slots;
    }

    @JsonProperty("slots")
    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

    public Session withSlots(List<String> slots) {
        this.slots = slots;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Session withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}