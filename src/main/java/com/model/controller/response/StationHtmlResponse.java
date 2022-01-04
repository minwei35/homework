package com.model.controller.response;

import com.model.pojo.StationHtml;
import lombok.Data;

@Data
public class StationHtmlResponse extends StationHtml {
    private String serviceName;

    public StationHtmlResponse(StationHtml t) {
        this.setId(t.getId());
        this.setStationServiceList(t.getStationServiceList());
        this.setHtmlPath(t.getHtmlPath());
        this.setStationName(t.getStationName());
    }
}
