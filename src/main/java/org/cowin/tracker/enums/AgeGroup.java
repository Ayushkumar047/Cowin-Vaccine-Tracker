package org.cowin.tracker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AgeGroup {
    EIGHTEEN_PLUS("18-45"),
    FORTY_FIVE_PLUS("45+");

    private final String age;
}
