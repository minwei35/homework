package com.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//站点信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationHtml {
    private Integer id;
    private String stationName;
    private String htmlPath;
    private List<StationService> stationServiceList;
    private Amenity amenity;
}
