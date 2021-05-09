package org.cowin.tracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse<T> {
    private List<T> data;
    private int total;

    public ListResponse(List<T> data) {
        this.data = data;
        this.total = data.size();
    }

}
