package org.cowin.tracker.repository;

import org.cowin.tracker.entity.Session;
import org.cowin.tracker.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long>, JpaSpecificationExecutor<Session> {

    Integer deleteByDistrictIdAndDateBetween(Long pincode, LocalDate from, LocalDate to);

    List<Session> findByPincodeAndDateBetweenAndMinAgeLimitAndFeeType(Long pincode, LocalDate from, LocalDate to, Long ageGroup, String feeType);

}
