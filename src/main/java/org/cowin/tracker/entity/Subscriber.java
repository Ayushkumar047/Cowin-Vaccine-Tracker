package org.cowin.tracker.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String email;

    @ElementCollection
    private List<Long> pincodes;

    @ElementCollection
    private List<Long> districts;

    @ElementCollection
    private List<String> vaccines;

    @ElementCollection
    private List<Long> ageGroups;

    @ElementCollection
    private List<String> feeTypes;

    @Transient
    private List<Session> sessions;
}
