package com.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//站点信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationHtml {
    private Integer id;
    private String stationName;
    private String htmlPath;

}
