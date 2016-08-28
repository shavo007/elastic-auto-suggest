package com.elastic.mvc.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class HotelResponse {

    @JsonProperty(value = "items")
    private List<Hotel> pointsData;

    public HotelResponse() {
        super();
    }

    public HotelResponse(List<Hotel> pointsData) {
        super();
        this.pointsData = pointsData;
    }
}
