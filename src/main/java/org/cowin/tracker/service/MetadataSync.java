package org.cowin.tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.cowin.tracker.api.CowinClient;
import org.cowin.tracker.entity.District;
import org.cowin.tracker.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class MetadataSync {
    @Autowired
    private CowinClient cowinClient;

    @Autowired
    private DistrictRepository districtRepository;

    private static final Long DELHI = 9L;

    @PostConstruct
    public void sync() {
        List<District> districts = cowinClient.getDistricts(DELHI).getBody().getDistricts();
        districts.forEach(district -> {
            district.setStateId(DELHI);
            district.setStateName("Delhi");
        });
        districtRepository.saveAll(districts);
    }
}
