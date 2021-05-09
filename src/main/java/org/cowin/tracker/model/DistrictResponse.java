package org.cowin.tracker.model;

import lombok.Data;
import org.cowin.tracker.entity.District;

import java.util.List;

@Data
public class DistrictResponse {
    private List<District> districts;
    private Long ttl;
}
