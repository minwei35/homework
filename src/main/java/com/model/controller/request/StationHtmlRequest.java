package com.model.controller.request;

import com.model.pojo.StationHtml;
import lombok.Data;

@Data
public class StationHtmlRequest extends StationHtml {
    private String[] stationServices;
}
