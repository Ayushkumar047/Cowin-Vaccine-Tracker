package org.cowin.tracker.repository;

import org.cowin.tracker.entity.Session;
import org.cowin.tracker.entity.Subscriber;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SessionSpecification implements Specification<Session> {

    private final Subscriber subscriber;

    public SessionSpecification(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Predicate toPredicate(Root<Session> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate p = criteriaBuilder.conjunction();
        if (!CollectionUtils.isEmpty(subscriber.getPincodes())) {
            p.getExpressions()
                    .add(root.get("pincode").in(subscriber.getPincodes()));
        }

        if (!CollectionUtils.isEmpty(subscriber.getDistricts())) {
            p.getExpressions()
                    .add(root.get("districtId").in(subscriber.getDistricts()));
        }

        if (!CollectionUtils.isEmpty(subscriber.getAgeGroups())) {
            p.getExpressions()
                    .add(root.get("minAgeLimit").in(subscriber.getAgeGroups()));
        }

        if (!CollectionUtils.isEmpty(subscriber.getFeeTypes())) {
            p.getExpressions()
                    .add(root.get("feeType").in(subscriber.getFeeTypes()));
        }

        if (!CollectionUtils.isEmpty(subscriber.getVaccines())) {
            p.getExpressions()
                    .add(root.get("vaccine").in(subscriber.getVaccines()));
        }
        return p;
    }
}
